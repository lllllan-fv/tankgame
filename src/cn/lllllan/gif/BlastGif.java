package cn.lllllan.gif;

import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class BlastGif extends Gif {


    public BlastGif(int x, int y) {
        super(x, y, 8);
    }

    @Override
    public void paint(Graphics g, ImageObserver observer) {
        if (!isLve()) return;
        String imgUrl = "/img/gif/blast/" + getLife() + ".gif";
        URL url = BarrierImpl.class.getResource(imgUrl);
        Image img = Toolkit.getDefaultToolkit().getImage(url);

        g.drawImage(img, getX(), getY(), Cube.getSIZE(), Cube.getSIZE(), observer);
        lifeDown();
    }

    @Override
    public void lifeDown() {
        if (isLve()) setLife(getLife() - 1);
    }
}
