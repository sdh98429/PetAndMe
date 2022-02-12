from django.urls import path
from . import views

app_name = 'taping'
urlpatterns = [
    path('tape/', views.pet_tape, name='tape'),
    path('test/', views.test, name='tape'),
]
