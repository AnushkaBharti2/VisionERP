@echo off
setlocal
set FAIL=0
for %%C in (java mvn node npm python docker) do (
  where %%C >nul 2>&1
  if errorlevel 1 (echo [MISSING] %%C & set FAIL=1) else (echo [OK] %%C)
)
if %FAIL%==0 (echo All required commands are available.) else (echo One or more commands are missing.)
endlocal
