package com.github.programmer3481.s3dlib;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Cube extends Object3D {

    public Cube(Shader shader, Vector3f position, Vector3f rotation, Vector3f scale) {
        super(shader, new Mesh(new Vertex[] {
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
    }), position, rotation, scale);
    }

}
