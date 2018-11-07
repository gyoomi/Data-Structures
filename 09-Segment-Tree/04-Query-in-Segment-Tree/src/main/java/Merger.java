/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 接口功能描述
 *
 * @author Leon
 * @version 2018/11/7 11:28
 */
@FunctionalInterface
public interface Merger<E> {

    E merge(E a, E b);
}
