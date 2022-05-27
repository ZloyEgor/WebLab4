package com.web.lab4.logic;

import com.web.lab4.entity.Dot;
import org.springframework.stereotype.Component;

@Component
public class AreaChecker {
    public boolean checkArea(Dot dot) {

        double x = dot.getX();
        double y = dot.getY();
        double r = dot.getR();

        if (r < 0)
            return false;

        if (r == 0)
            return x == 0 && y == 0;

        if (x >= 0 && y >= 0) {
            return x <= r && y <= r / 2.;
        }

        if (x <= 0 && y >= 0) {
            return y <= x + r;
        }

        if (x <= 0 && y <= 0) {
            return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2);
        }

        return false;
    }
}
