# 俄罗斯方块项目

这是一个基于Java Swing实现的俄罗斯方块游戏，具有AI功能。

## 项目结构

```
TetrisProject/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── tetris/
│   │               ├── model/
│   │               │   ├── Shape.java
│   │               │   ├── Board.java
│   │               │   └── GameState.java
│   │               ├── view/
│   │               │   ├── GamePanel.java
│   │               │   ├── StatusPanel.java
│   │               │   └── ControlPanel.java
│   │               ├── controller/
│   │               │   ├── GameController.java
│   │               │   ├── AIController.java
│   │               │   └── InputHandler.java
│   │               └── main/
│   │                   └── TetrisGame.java
│   └── test/
│       └── java/
│           └── com/
│               └── tetris/
│                   └── test/
│                       ├── ShapeTest.java
│                       └── BoardTest.java
├── resources/
│   ├── images/
│   └── sounds/
└── README.md
```

## 功能特点

1. **完整的游戏功能**
   - 方块移动、旋转、下落
   - 消行计分
   - 游戏暂停/继续
   - 游戏结束判定

2. **AI模式**
   - 自动控制方块移动
   - 简单的AI算法评估最佳位置
   - 可切换手动/AI模式

3. **用户界面**
   - 游戏主面板
   - 状态显示面板
   - 控制按钮面板

## 运行说明

### 编译和运行

1. 使用命令行编译：
```bash
# 编译
javac -d bin src/main/java/com/tetris/main/TetrisGame.java

# 运行
java -cp bin com.tetris.main.TetrisGame
```

2. 使用IDE（如Eclipse或IntelliJ IDEA）：
   - 导入项目
   - 运行 `TetrisGame.java` 主类

### 操作说明

- **方向键左/右**：移动方块
- **方向键上/下**：旋转方块
- **空格键**：快速下落
- **P键**：暂停/继续游戏
- **A键**：切换AI模式

## 技术架构

项目采用MVC（Model-View-Controller）设计模式：

- **Model层**：游戏核心逻辑
  - `Shape.java`：方块形状和旋转
  - `Board.java`：游戏面板和消行逻辑
  - `GameState.java`：游戏状态管理

- **View层**：用户界面
  - `GamePanel.java`：游戏主面板
  - `StatusPanel.java`：状态显示
  - `ControlPanel.java`：控制按钮

- **Controller层**：业务逻辑控制
  - `GameController.java`：游戏主控制器
  - `AIController.java`：AI控制逻辑
  - `InputHandler.java`：键盘输入处理

## 测试

运行测试：
```bash
# 编译测试
javac -d bin-test src/test/java/com/tetris/test/*.java

# 运行测试
java -cp bin-test:bin org.junit.jupiter.api.runner.JUnitCore com.tetris.test.ShapeTest
java -cp bin-test:bin org.junit.jupiter.api.runner.JUnitCore com.tetris.test.BoardTest
```

## 开发说明

1. **扩展性**：项目结构清晰，便于添加新功能
2. **可维护性**：各模块职责明确，耦合度低
3. **可测试性**：核心逻辑有单元测试覆盖

## 许可证

本项目仅供学习和参考使用。