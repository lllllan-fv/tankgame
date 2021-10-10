package cn.lllllan.gif;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class HitGif extends Gif {

    private String imgUrl = "/img/gif/hit.gif";
    private URL url = BarrierImpl.class.getResource(imgUrl);
    private Image img = Toolkit.getDefaultToolkit().getImage(url);

    public HitGif(int x, int y) {
        super(x, y, 4);
    }

    @Override
    public void paint(Graphics g, ImageObserver observer) {
        if (!isLve()) return;
        int size = BulletImpl.getSIZE() * getLife();

        int x = getX() - size / 2;
        int y = getY() - size / 2;

        g.drawImage(img, x, y, size, size, observer);

        lifeDown();
    }

    @Override
    public void lifeDown() {
        if (isLve()) setLife(getLife() - 1);
    }
}
