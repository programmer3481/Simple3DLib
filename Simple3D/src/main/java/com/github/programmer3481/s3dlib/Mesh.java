package com.github.programmer3481.s3dlib;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.joml.Vector3f;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private Vertex[] vertices;
    private int[] indices;
    private int vao, vbo, tbo, cbo, ibo, nbo;

    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] vertexData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            vertexData[i * 3] = vertices[i].getPosition().x();
            vertexData[i * 3 + 1] = vertices[i].getPosition().y();
            vertexData[i * 3 + 2] = vertices[i].getPosition().z();
        }
        vertexBuffer.put(vertexData).flip();

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
        float[] textureData = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i++)  {
            textureData[i * 2] = vertices[i].getTexCoord().x();
            textureData[i * 2 + 1] = vertices[i].getTexCoord().y();
        }
        textureBuffer.put(textureData).flip();

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] colorData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            colorData[i * 3] = vertices[i].getColor().x();
            colorData[i * 3 + 1] = vertices[i].getColor().y();
            colorData[i * 3 + 2] = vertices[i].getColor().z();
        }
        colorBuffer.put(colorData).flip();

        cbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, cbo);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FloatBuffer normalBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] normalData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            normalData[i * 3 + 0] = vertices[i].getNormal().x();
            normalData[i * 3 + 1] = vertices[i].getNormal().y();
            normalData[i * 3 + 2] = vertices[i].getNormal().z();
        }
        normalBuffer.put(normalData).flip();

        nbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER, normalBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(3, 3, GL_FLOAT, false, 0, 0);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void destroy() {
        glDeleteBuffers(vbo);
        glDeleteBuffers(ibo);
        glDeleteBuffers(tbo);
        glDeleteBuffers(cbo);
        glDeleteBuffers(nbo);
        glDeleteVertexArrays(vao);
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public int getVAO() {
        return vao;
    }

    public int getPBO() {
        return vbo;
    }

    public int getTBO() {
        return tbo;
    }

    public int getCBO() {
        return cbo;
    }

    public int getNBO() {
        return nbo;
    }

    public int getIBO() {
        return ibo;
    }

    public Mesh Cube() {
        return new Mesh(new Vertex[] {
            new Vertex(new Vector3f(-1.0f, -1.0f, -1.0f), new Vector2f(1.0f, 2.0f/4)), //v0
            new Vertex(new Vector3f(1.0f, -1.0f, -1.0f), new Vector2f(2.0f/3, 2.0f/4)), //v1
            new Vertex(new Vector3f(1.0f, 1.0f, -1.0f), new Vector2f(2.0f/3, 3.0f/4)), //v2
            new Vertex(new Vector3f(-1.0f, 1.0f, -1.0f), new Vector2f(1.0f, 3.0f/4)), //v3
            new Vertex(new Vector3f(-1.0f, -1.0f, 1.0f), new Vector2f(0.0f, 2.0f/4)), //v4
            new Vertex(new Vector3f(1.0f, -1.0f, 1.0f), new Vector2f(1.0f/3, 2.0f/4)), //v5
            new Vertex(new Vector3f(1.0f, 1.0f, 1.0f), new Vector2f(1.0f/3, 3.0f/4)), //v6
            new Vertex(new Vector3f(-1.0f, 1.0f, 1.0f), new Vector2f(0.0f, 3.0f/4)), //7

            new Vertex(new Vector3f(-1.0f, -1.0f, -1.0f), new Vector2f(2.0f/3, 1.0f/4)), //v8
            new Vertex(new Vector3f(-1.0f, 1.0f, -1.0f), new Vector2f(2.0f/3, 1.0f)), //v9
            new Vertex(new Vector3f(-1.0f, 1.0f, 1.0f), new Vector2f(1.0f/3, 1.0f)), //10
            new Vertex(new Vector3f(-1.0f, -1.0f, 1.0f), new Vector2f(1.0f/3, 1.0f/4)), //v11

            new Vertex(new Vector3f(-1.0f, 1.0f, -1.0f), new Vector2f(2.0f/3, 0.0f)), //v12
            new Vertex(new Vector3f(-1.0f, 1.0f, 1.0f), new Vector2f(1.0f/3, 0.0f)) //13
    }, new int[] {
            5, 2, 6,
            5, 1, 2,

            4, 6, 7,
            4, 5, 6,

            1, 3, 2,
            1, 0, 3,

            6, 9, 10,
            6, 2, 9,

            1, 11, 8,
            1, 5, 11,

            8, 13, 12,
            8, 11, 13
    });
    }
}
