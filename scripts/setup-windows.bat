@echo off
setlocal
cd /d "%~dp0.."

echo [VisionERP] Checking local configuration...
if not exist .env (
  copy /Y .env.example .env >nul
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$p=Get-Content .env; $jwt=[Convert]::ToBase64String((1..48 ^| ForEach-Object {Get-Random -Maximum 256})); $pwd='Verp-' + [guid]::NewGuid().ToString('N').Substring(0,18) + '!'; $p=$p -replace '^POSTGRES_PASSWORD=.*$', ('POSTGRES_PASSWORD=' + $pwd); $p=$p -replace '^JWT_SECRET=.*$', ('JWT_SECRET=visionerp_local_jwt_secret_' + $jwt); $p=$p -replace '^SEED_ADMIN_EMAIL=.*$', 'SEED_ADMIN_EMAIL=admin@visionerp.local'; $p=$p -replace '^SEED_ADMIN_PASSWORD=.*$', ('SEED_ADMIN_PASSWORD=' + $pwd); Set-Content -Encoding UTF8 .env $p; Set-Content -Encoding UTF8 .visionerp-admin-password $pwd"
  if errorlevel 1 (
    echo ERROR: Could not create .env automatically.
    exit /b 1
  )
  echo Created .env for local development.
) else (
  echo .env already exists; leaving it unchanged.
)

echo.
echo [VisionERP] Checking Docker CLI...
docker --version >nul 2>&1
if errorlevel 1 (
  echo ERROR: Docker CLI is not available.
  echo Start Docker Desktop, then open a NEW Command Prompt and run this script again.
  exit /b 1
)

echo [VisionERP] Checking Docker engine...
docker info >nul 2>&1
if errorlevel 1 (
  echo ERROR: Docker Desktop is installed but its Linux engine is not running.
  echo.
  echo Please:
  echo   1. Start Docker Desktop.
  echo   2. Wait until Docker Desktop says it is running.
  echo   3. If it still fails, finish the WSL2 setup with: wsl --status
  echo   4. Then run this script again.
  exit /b 1
)

echo [VisionERP] Starting the complete stack...
docker compose up --build -d
if errorlevel 1 (
  echo ERROR: Docker Compose failed.
  echo Run: docker compose logs
  exit /b 1
)

echo.
echo VisionERP is starting.
echo Frontend: http://localhost:5173
echo Backend:  http://localhost:8080
echo Swagger:  http://localhost:8080/swagger-ui.html
echo AI:       http://localhost:8000/health
echo Login:    admin@visionerp.local ^(password saved in .visionerp-admin-password^)
endlocal
