@echo off
echo. 
echo. 
echo. 
set/p key=***�����������ǰλ����svn/eclipse/idea/log�Ⱥۼ�,����������������ɾ����ر�***:
for /r . %%a in (.) do (
	@if exist "%%a\.settings" rd /s /q "%%a\.settings" 
	@if exist "%%a\.idea" rd /s /q "%%a\.idea" 
	@if exist "%%a\target" rd /s /q "%%a\target"
	@if exist "%%a\.svn" rd /s /q "%%a\.svn" 
	@if exist "%%a\.project" del "%%a\.project" 
	@if exist "%%a\.classpath" del "%%a\.classpath"
        @if exist "%%a\*.iml" del "%%a\*.iml"
	@if exist "%%a\.gitignore" del "%%a\.gitignore"
	@if exist "%%a\.log" del "%%a\.log"
echo ɾ���ۼ���...
)
echo ������,�밴������ر������...
pause > nul