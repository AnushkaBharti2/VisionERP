@echo off
cd /d "%~dp0.."
echo WARNING: this removes the VisionERP database volume and all local demo data.
choice /M "Continue"
if errorlevel 2 exit /b 0
docker compose down -v
echo Database reset complete.
