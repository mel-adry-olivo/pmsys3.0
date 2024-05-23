package org.pmsys.main.ui.components.base;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

// got from GitHub

public class AvatarIcon implements Icon {
    private final Icon icon;
    private final int width;
    private final int height;
    private Image image;
    private int round;
    private int imageWidth;
    private int imageHeight;
    private boolean imageUpdated;

    public AvatarIcon(Icon icon, int width, int height, int round) {
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.round = round;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (!imageUpdated) {
            updateImage();
        }

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            FlatUIUtils.paintComponentBackground(g2, x, y, imageWidth, imageHeight, 0, UIScale.scale(round));
            g2.drawImage(image, x, y, null);
        } finally {
            g2.dispose();
        }
    }

    private void updateImage() {
        if (icon != null && image == null) {
            imageWidth = UIScale.scale(width);
            imageHeight = UIScale.scale(height);

            if (icon instanceof ImageIcon imageIcon) {
                image = resizeImage(imageIcon.getImage(), imageWidth, imageHeight);
                imageUpdated = true;
            } else {
                throw new IllegalArgumentException("Unsupported icon type. Only ImageIcon is supported.");
            }
        }
    }

    private Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        int newWidth;
        int newHeight;

        if (originalWidth * targetHeight > originalHeight * targetWidth) {
            newWidth = targetWidth;
            newHeight = (originalHeight * targetWidth) / originalWidth;
        } else {
            newWidth = (originalWidth * targetHeight) / originalHeight;
            newHeight = targetHeight;
        }

        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        if (round > 0) {
            return roundImage(scaledImage, targetWidth, targetHeight, round);
        } else {
            return scaledImage;
        }
    }

    private Image roundImage(Image image, int width, int height, int round) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();

        try {
            FlatUIUtils.setRenderingHints(g2);

            if (round == 999) {
                g2.fill(new Ellipse2D.Double(0, 0, width, height));
            } else {
                int scaledRound = UIScale.scale(round);
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height, scaledRound, scaledRound));
            }

            g2.setComposite(AlphaComposite.SrcIn);
            int x = (width - image.getWidth(null)) / 2;
            int y = (height - image.getHeight(null)) / 2;
            g2.drawImage(image, x, y, null);
        } finally {
            g2.dispose();
        }

        return bufferedImage;
    }

    @Override
    public int getIconWidth() {
        if (!imageUpdated) {
            updateImage();
        }
        return imageWidth;
    }

    @Override
    public int getIconHeight() {
        if (!imageUpdated) {
            updateImage();
        }
        return imageHeight;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        imageUpdated = false; // Mark image to be updated
    }
}
