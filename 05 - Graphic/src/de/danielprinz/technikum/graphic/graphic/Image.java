package de.danielprinz.technikum.graphic.graphic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by el17x002 on 15.10.2018.
 */
public class Image extends Figure {

    private File file;
    private BufferedImage bufferedImage;

    public Image(File file, final int WIDTH, int HEIGHT) throws IOException {
        super(0, 0);

        this.file = file;
        this.bufferedImage = ImageIO.read(file);

        double widthRel = WIDTH / (double) bufferedImage.getWidth();
        double heightRel = HEIGHT / (double) bufferedImage.getHeight();

        if(widthRel < 1 || heightRel < 1) {
            int newWidth, newHeight;
            if(widthRel < heightRel) {
                newWidth = WIDTH;
                newHeight = (int) (bufferedImage.getHeight() * widthRel);
            } else {
                newWidth = (int) (bufferedImage.getWidth() * heightRel);
                newHeight = HEIGHT;
            }
            resize(newWidth, newHeight);
        }

        this.x = (WIDTH - bufferedImage.getWidth()) / 2;
        this.y = (HEIGHT - bufferedImage.getHeight()) / 2;
    }


    @Override
    public boolean collides(MouseEvent e) {
        return false;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bufferedImage, this.x, this.y, null);
    }


    private void resize(int newWidth, int newHeight) {
        java.awt.Image tmp = bufferedImage.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        this.bufferedImage = dimg;
    }

}
