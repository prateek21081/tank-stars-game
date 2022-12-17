package com.tankstars.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;

public class Terrain {
    private final int WIDTH;
    private final int HEIGHT;
    private Pixmap pixmap;
    private List<Integer> yCoordinates;
    private Texture texture;

    public Terrain(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        initYCoordinates();
        generateTexture();
    }

    public List<Integer> getyCoordinates() {
        return yCoordinates;
    }

    private void initYCoordinates() {
        yCoordinates = new ArrayList<Integer>();
        for (int i = 0; i < WIDTH; i++) {
            yCoordinates.add(200);
        }
    }

    int convertY(int y) {
        return HEIGHT - y;
    }

    private int getDeformedYCoordinate(int x, int h, int k, int r) {
        // quadratic formula to get lower y coordinate of a circle shifted to (h, k) with radius 'r'
        return (int) Math.floor((2 * k - Math.sqrt(4*k*k - 4*(x*x + h*h - 2*h*x + k*k - r*r))) / 2.0);
    }

    public void deformAt(int h, int k, int r) {
        int x1 = Math.max(0, h - r);
        int x2 = Math.min(WIDTH, h + r);

        for (int x = x1; x < x2; x++) {
            int y = getDeformedYCoordinate(x, h, k, r);
//             y = convertY(y);
            y = Math.min(yCoordinates.get(x), y);
            yCoordinates.set(x, y);
        }
        generateTexture();
    }

    private void generatePixmap() {
        pixmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (y >= convertY(yCoordinates.get(x))) {
                    int green = Color.rgba8888(0.165f, 0.0313f, 0.1568f, 1);
                    pixmap.drawPixel(x, y, green);
                } else {
                    int transparent = Color.rgba8888(0, 1, 0, 0);
                    pixmap.drawPixel(x, y, transparent);
                }
            }
        }
    }

    private void generateTexture() {
        generatePixmap();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public Texture getTexture() {
        return texture;
    }
}