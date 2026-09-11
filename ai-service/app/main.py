from fastapi import FastAPI, UploadFile, File, HTTPException
from pydantic import BaseModel
import cv2, numpy as np, hashlib
app=FastAPI(title='VisionERP AI Service',version='1.0.0')
face_detector=cv2.CascadeClassifier(cv2.data.haarcascades+'haarcascade_frontalface_default.xml')
class Detection(BaseModel): faces:int; image_width:int; image_height:int; quality_score:float
class VisionResult(BaseModel): faces:int; boxes:list; quality_score:float; image_sha256:str
@app.get('/health')
def health(): return {'status':'ok','service':'visionerp-ai','capabilities':['face-detection','image-quality','assistant-tool-contract']}
def analyze(img):
    gray=cv2.cvtColor(img,cv2.COLOR_BGR2GRAY) if len(img.shape)==3 else img
    faces=face_detector.detectMultiScale(gray,1.1,5,minSize=(60,60))
    lap=cv2.Laplacian(gray,cv2.CV_64F).var(); quality=float(min(1.0,lap/500.0))
    return faces,quality
@app.post('/vision/analyze',response_model=VisionResult)
async def analyze_image(file:UploadFile=File(...)):
    data=await file.read(); arr=np.frombuffer(data,np.uint8); img=cv2.imdecode(arr,cv2.IMREAD_COLOR)
    if img is None: raise HTTPException(400,'Unsupported image')
    faces,q=analyze(img)
    return VisionResult(faces=len(faces),boxes=[{'x':int(x),'y':int(y),'width':int(w),'height':int(h)} for x,y,w,h in faces],quality_score=round(q,3),image_sha256=hashlib.sha256(data).hexdigest())
@app.get('/assistant/tools')
def tools(): return {'tools':['get_attendance_summary','get_department_attendance','get_visitor_statistics'],'security':'Core backend must enforce authorization before executing tools.'}
