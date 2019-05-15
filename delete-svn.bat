@echo off
echo. 
echo. 
echo. 
set/p key=***本命令清除当前位置下svn/eclipse/idea/log等痕迹,精简程序包，如无需删除请关闭***:
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
echo 删除痕迹中...
)
echo 清除完毕,请按任意键关闭命令窗口...
pause > nul