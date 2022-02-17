from urllib import request, response
from rest_framework.response import Response
from django.shortcuts import render
from rest_framework.decorators import api_view
from django.http.response import HttpResponse
import datetime

# Video
from moviepy.editor import *
from moviepy.video.tools.segmenting import findObjects
import numpy as np
import cv2

# Image
from PIL import Image, ImageOps
import glob
from natsort import natsorted
import base64
import numpy as np

# path
import os
import shutil

# list test
from rest_framework.decorators import api_view

# Create your views here.

@api_view(['POST'])
def pet_tape(request):
    # user Id
    userId = request.data.get('userId')

    # base64 encoded video list
    videoList = []

    if request.method == "POST":
        # userId로 만들어진 dir 있는지 확인 / 없으면 만들기
        try:
            os.mkdir('./taping/' + userId)
        except:
            pass
        # data parsing
        movieType = request.data.get('type')
        movieBgm = request.data.get('bgm')
        movieImages = request.data.get('images')
        # 넘어온 images list 돌면서 base64 decode
        imageIdx = 0
        for (idx, image) in enumerate(movieImages):
            if image:
                imgdata = base64.b64decode(image)
                filename = userId + str(idx) + 'image.jpg'
                imageIdx += 1
                with open(filename, 'wb') as f:
                    f.write(imgdata)

                if movieType == "2":
                    typeTrans = cv2.imread(filename, cv2.IMREAD_COLOR)
                    cartoon_img = cv2.stylization(typeTrans, sigma_s=100, sigma_r=0.9)
                    cv2.imwrite(filename, cartoon_img)

                elif movieType == "3":
                    typeTrans = Image.open(filename)
                    newone = typeTrans.transpose(Image.FLIP_LEFT_RIGHT)
                    newone.save(filename)
                # elif movieType == "4":
                #     typeTrans = Image.open(filename).convert('L')
                #     typeTrans.save(filename)

    origin = os.path.dirname(os.path.abspath(__file__))
    originPath = os.path.abspath(os.path.join(origin, '..'))
    audiodir = origin + '/audiofiles'
    videodir = origin + '/' + userId
    userdir = origin + '/' + userId
    print(origin)
    # 폴더 내 모든 jpeg 파일 가져와서 정렬
    file_list = glob.glob(os.path.join(originPath, '*.jpg'))

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
    bgm = AudioFileClip(os.path.join(audiodir, str(movieBgm)+'.mp3')).set_duration(final_clip.duration)
    clips = final_clip.set_audio(bgm)

    # video indexing
    idx = str(datetime.date.today())
    videoname = userId + idx
    originPath = os.path.abspath(os.path.join(origin, '..'))

    # 렌더링
    clips.write_videofile(videoname + '.mp4', fps=12, codec='libx264')

    # 유저별 경로로 이동
    shutil.move(originPath + '/' + videoname + '.mp4', userdir + '/' + videoname + '.mp4')

    # base64 decode한 사진 삭제
    for j in range(imageIdx):
        removefilename = os.path.join(originPath) + '/' + userId + str(j) + 'image.jpg'
        os.remove(removefilename)

    # return (clips)
    # 폴더 내 모든 mp4 파일 가져와서 정렬
    video_list = glob.glob(os.path.join(videodir, '*.mp4'))
    video_list_sorted = natsorted(video_list,reverse=False)
    # video encode
    vid_list = [] 
    with open(video_list_sorted[-1], "rb") as vidStr:
        convert = base64.b64encode(vidStr.read())
    vid_list.append(convert)

    return Response(vid_list)
    
@api_view(['POST'])
def returntape(request):
    userId = request.data.get('returnUserId')
    origin = os.path.dirname(os.path.abspath(__file__))
    videodir = origin + '/' + userId

    video_list = glob.glob(os.path.join(videodir, '*.mp4'))
    video_list_sorted = natsorted(video_list,reverse=False)
    # video encode
    vid_list = []
    for vid in video_list_sorted:
        with open(vid, "rb") as vidStr:
            convert = base64.b64encode(vidStr.read())
        vid_list.append(convert)
    return Response(vid_list)
