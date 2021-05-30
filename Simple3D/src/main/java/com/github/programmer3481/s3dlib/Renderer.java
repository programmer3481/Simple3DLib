package com.github.programmer3481.s3dlib;

import org.joml.Matrix4f;
import org.joml.Quaternionf;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    public void render(Object3D object, Camera camera) {
        glBindVertexArray(object.getMesh().getVAO());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());

        for (int i = 0; i < object.getShader().getTexture().length; i++) {
            glActiveTexture(GL_TEXTURE0 + i);
            glBindTexture(GL_TEXTURE_2D, object.getShader().getTexture()[i].getTextureID());
        }
        glUseProgram(object.getShader().getProgramID());
		
		Matrix4f model = new Matrix4f();
		model.scale(object.getScale());
		model.rotate((new Quaternionf()).rotateXYZ(
                            (float) Math.toRadians(object.getRotation().x),
                            (float) Math.toRadians(object.getRotation().y),
                            (float) Math.toRadians(object.getRotation().z)));
		model.translate(object.getPosition());
		
		Matrix4f view = new Matrix4f();
		view.rotate((new Quaternionf()).rotateXYZ(
                        (float) -Math.toRadians(camera.getRotation().x),
                        (float) -Math.toRadians(camera.getRotation().y),
                        (float) -Math.toRadians(camera.getRotation().z)));
		view.translate(-camera.getPosition().x, -camera.getPosition().y, -camera.getPosition().z);
		object.getShader().setUniform("matrix", (new Matrix4f()).mul(camera.getViewMatrix()).mul(view).mul(model));
        glDrawElements(GL_TRIANGLES, object.getMesh().getIndices().length, GL_UNSIGNED_INT, 0);
        glUseProgram(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
