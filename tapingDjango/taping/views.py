from django.shortcuts import render

# Video
from moviepy.editor import *
from moviepy.video.tools.segmenting import findObjects
import numpy as np

# Image
from PIL import Image, ImageOps
import glob
from natsort import natsorted

# path
import os

# Create your views here.

def pet_tape(request):
    origin = os.path.dirname(os.path.abspath(__file__))
    audiodir = origin + '/audiofiles'
    picdir = origin + '/pics'
    # 폴더 내 모든 jpeg 파일 가져와서 정렬
    file_list = glob.glob(os.path.join(picdir, '*.jpg'))
    # print(origin)
    # print(os.path.join(picdir))
    # print(file_list)
    # resizing prevent rotate
    '''
    일반적으로 세로 > 가로면 rotate정보가 EXIF 메타데이터에 저장되고, PIL은 이를 자동으로 읽어 사진을 rotate한다.
    이를 방지하기 위해 ImageOps.exif_transpose를 활용하여 이미지를 fix한다.
    '''
    for i in file_list:
        image = Image.open(i)
        image = ImageOps.exif_transpose(image)
        resized = image.resize((1280, 720))
        resized.save(i)

    file_list_sorted = natsorted(file_list,reverse=False)

    # 각 사진당 2초의 시간을 두고 이어붙이기
    clips = [ImageClip(m).set_duration(2) for m in file_list_sorted]
    # 비디오로 연결
    video = concatenate_videoclips(clips, method="compose")

    # 인트로 영상 자막용 사이즈 체크
    screensize = video.size

    # 인트로 자막 생성
    txtClip = TextClip('Pet & Me',color='white', font="Amiri-Bold", kerning = 5, fontsize=150)
    cvc = CompositeVideoClip([txtClip.set_pos('center')], size=screensize)

    # 좌표 선정
    rotMatrix = lambda a: np.array( [[np.cos(a),np.sin(a)], [-np.sin(a),np.cos(a)]] )

    # 자막 애니메이션
    def arrive(screenpos,i,nletters):
        v = np.array([-1,0])
        d = lambda t : max(0, 3-3*t)
        return lambda t: screenpos-400*v*d(t-0.2*i)
        
    def vortexout(screenpos,i,nletters):
        d = lambda t : max(0,t)
        a = i*np.pi/ nletters
        v = rotMatrix(a).dot([-1,0])
        if i%2:
            v[1] = -v[1]
        return lambda t: screenpos+400*d(t-0.1*i)*rotMatrix(-0.2*d(t)*a).dot(v)

    def cascade(screenpos,i,nletters):
        v = np.array([0,-1])
        d = lambda t : 1 if t<0 else abs(np.sinc(t)/(1+t**4))
        return lambda t: screenpos+v*400*d(t-0.15*i)

    # 문자열 저장
    letters = findObjects(cvc)

    # 애니메이션화
    def moveLetters(letters, funcpos):
        return [ letter.set_pos(funcpos(letter.screenpos,i,len(letters))) for i,letter in enumerate(letters)]

    # 인트로 + video + 아웃트로붙이기
    clips = [CompositeVideoClip(moveLetters(letters,funcpos), size = screensize).subclip(0,3) for funcpos in [arrive, vortexout]] + [video] + [CompositeVideoClip(moveLetters(letters,funcpos), size = screensize).subclip(0,5) for funcpos in [cascade]]

    # 영상 길이에 맞춰 bgm duration 설정
    final_clip = concatenate_videoclips(clips)
    bgm = AudioFileClip(os.path.join(audiodir, 'optimistic.mp3')).set_duration(final_clip.duration)
    clips = final_clip.set_audio(bgm)

    # 렌더링
    clips.write_videofile('PetNMe.mp4',fps=12, codec='libx264')
    return (clips)