package laurel.util.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

@SuppressWarnings("unused")
public class Shader {

    private static final Minecraft mc = Minecraft.getMinecraft();

    private static final String VERTEX_SHADER = "#version 120\n" +
            "\n" +
            "void main() {\n" +
            "    gl_TexCoord[0] = gl_MultiTexCoord0;\n" +
            "    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
            "}";

    private final int programID;

    public Shader(String fragmentShader) {
        int program = glCreateProgram();

        int fragmentShaderID = createShader(fragmentShader, GL_FRAGMENT_SHADER);
        glAttachShader(program, fragmentShaderID);
        int vertexShaderID = createShader(VERTEX_SHADER, GL_VERTEX_SHADER);
        glAttachShader(program, vertexShaderID);

        glLinkProgram(program);

        this.programID = program;
    }

    public void init() {
        glUseProgram(programID);
    }

    public void unload() {
        glUseProgram(0);
    }

    public void setUniformf(String name, float... args) {
        int uniformLocation = glGetUniformLocation(programID, name);

        switch (args.length) {
            case 1:
                glUniform1f(uniformLocation, args[0]);
                break;
            case 2:
                glUniform2f(uniformLocation, args[0], args[1]);
                break;
            case 3:
                glUniform3f(uniformLocation, args[0], args[1], args[2]);
                break;
            case 4:
                glUniform4f(uniformLocation, args[0], args[1], args[2], args[3]);
                break;
        }
    }

    public void setUniformi(String name, int... args) {
        int uniformLocation = glGetUniformLocation(programID, name);

        if (args.length == 1) {
            glUniform1i(uniformLocation, args[0]);
        } else {
            glUniform2i(uniformLocation, args[0], args[1]);
        }
    }

    public static void drawQuads(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glTexCoord2f(0, 1);
        glVertex2f(x, y + height);
        glTexCoord2f(1, 1);
        glVertex2f(x + width, y + height);
        glTexCoord2f(1, 0);
        glVertex2f(x + width, y);
        glEnd();
    }

    private int createShader(String string, int shaderType) {
        int shader = glCreateShader(shaderType);
        glShaderSource(shader, string);
        glCompileShader(shader);

        return shader;
    }
}