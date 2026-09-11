@echo off
setlocal
cd /d "%~dp0.."
if not exist .env call scripts\setup-windows.bat
if errorlevel 1 exit /b 1
docker compose up -d
if errorlevel 1 exit /b 1
echo.
echo VisionERP: http://localhost:5173
endlocal
