/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 接口功能描述
 *
 * @author Leon
 * @version 2018/11/9 15:56
 */
public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
