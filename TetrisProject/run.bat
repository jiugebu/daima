@echo off
echo 正在启动俄罗斯方块游戏...
echo.

REM 设置Java类路径，包含bin目录下的所有class文件
set CLASSPATH=bin;.

REM 运行主类
java -cp %CLASSPATH% com.tetris.main.TetrisGame

echo.
echo 游戏已退出。
pause