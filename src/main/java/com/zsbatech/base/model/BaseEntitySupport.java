package com.zsbatech.base.model;

import com.google.common.base.MoreObjects;

import java.util.HashMap;

/**
 * description: 实体类需要继承此抽象类，实现序列化及重写
 * 继承并重写此抽象类后，可以不进行equals 和 toString 方法重写
 *
 * @author wxcsdb88
 * @since 2017/11/7 15:30
 */
public abstract class BaseEntitySupport implements Entity {
    private static final long serialVersionUID = -1L;

    @Override
    public boolean newCreate() {
        return (getId() == null) || (getId() <= 0L);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + getClass().getName().hashCode();
        result = result * 31 + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        Entity that = (Entity) obj;
        return (that.getId() != null) && (that.getId().equals(getId()));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId() == null ? "NULL" : getId())
                .toString();
    }
}
