package com.github.programmer3481.s3dlib;

import org.joml.Vector3f;
import org.joml.Matrix4f;

public class Camera {
    private Vector3f position;
    private Vector3f rotation;
    private Matrix4f view;

    public Camera(Vector3f position, Vector3f rotation, float fov, float aspect, float near, float far) {
        this.position = position;
        this.rotation = rotation;
        view = new Matrix4f();
        view.setPerspective(fov, aspect, near, far);
    }

    public Camera(Vector3f position, Vector3f rotation, float left, float right, float bottom, float top, float near, float far) {
        this.position = position;
        this.rotation = rotation;
        view = new Matrix4f();
        view.ortho(left, right, bottom, top, near, far);
    }



    public Vector3f getRotation() {
        return rotation;
    }

    public Matrix4f getViewMatrix() {
        return view;
    }

    public Vector3f getPosition() {
        return position;
    }
}
