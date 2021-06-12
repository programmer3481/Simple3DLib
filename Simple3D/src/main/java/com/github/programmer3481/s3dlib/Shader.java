package com.github.programmer3481.s3dlib;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class Shader {
    private String vertexFile, fragmentFile;
    private int programID, vertexID, fragmentID;
    private Texture texture[];

    public Shader(String vert, String frag, Texture[] textures) {
        try {
            vertexFile = Files.readString(Path.of(vert));
            fragmentFile = Files.readString(Path.of(frag));
        } catch (IOException e) {
            e.printStackTrace();
        }

        programID = glCreateProgram();
        vertexID = glCreateShader(GL_VERTEX_SHADER);

        glShaderSource(vertexID, vertexFile);
        glCompileShader(vertexID);

        if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Vertex Shader: " + glGetShaderInfoLog(vertexID));
            return;
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(fragmentID, fragmentFile);
        glCompileShader(fragmentID);

        if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Fragment Shader: " + glGetShaderInfoLog(fragmentID));
            return;
        }

        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);

        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Program Linking: " + glGetProgramInfoLog(programID));
            return;
        }

        glValidateProgram(programID);
        if (glGetProgrami(programID, GL_VALIDATE_STATUS) == GL_FALSE) {
            System.err.println("Program Validating: " + glGetProgramInfoLog(programID));
            return;
        }

        glDetachShader(programID, vertexID);
        glDetachShader(programID, fragmentID);
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        this.texture = textures;

        for (int i = 0; i < textures.length; i++) {
            setUniform("tex" + (i + 1), i);
        }
    }

    public Shader(String vert, String frag, Texture texture) {
        this(vert, frag, new Texture[] {texture});
    }

    public Shader(String vert, String frag) {
        this(vert, frag, new Texture[] {});
    }

    public void destroy() {
        glDeleteProgram(programID);
    }

    public int getProgramID() {
        return programID;
    }

    public int getUniformLocation(String name) {
        return glGetUniformLocation(programID, name);
    }

    public void setUniform(String name, float value) {
        glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform(String name, int value) {
        glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform(String name, boolean value) {
        glUniform1i(getUniformLocation(name), value ? 1 : 0);
    }

    public void setUniform(String name, Vector2f value) {
        glUniform2f(getUniformLocation(name), value.x(), value.y());
    }

    public void setUniform(String name, Vector3f value) {
        glUniform3f(getUniformLocation(name), value.x(), value.y(), value.z());
    }

    public void setUniform(String name, Matrix4f value) {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(4 * 4 );
        matrix.put(value.get(new float[16])).flip();
        glUniformMatrix4fv(getUniformLocation(name), false, matrix);
    }

    public Texture[] getTexture() {
        return texture;
    }
}
