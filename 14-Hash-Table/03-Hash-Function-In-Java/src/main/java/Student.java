/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/21 14:31
 */
public class Student {

    int grade;
    int cls;
    String firstName;
    String lastName;

    public Student(int grade, int cls, String firstName, String lastName) {
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + ((Integer) grade).hashCode();
        hash = hash * B + ((Integer) cls).hashCode();
        hash = hash * B + firstName.hashCode();
        hash = hash * B + lastName.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Student another = (Student) obj;
        return another.grade == this.grade &&
                another.cls == this.cls &&
                another.firstName.equals(this.firstName) &&
                another.lastName.equals(this.lastName);
    }
}
