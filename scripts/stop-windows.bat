@echo off
cd /d "%~dp0.."
docker compose down
echo VisionERP containers stopped. Database volume is preserved.
