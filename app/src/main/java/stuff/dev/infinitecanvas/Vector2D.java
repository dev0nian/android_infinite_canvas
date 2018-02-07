package stuff.dev.infinitecanvas;

import android.graphics.PointF;

public class Vector2D {
    private float mX = 0.0f;
    private float mY = 0.0f;

    public Vector2D() {
        mX = 0.0f;
        mY = 0.0f;
    }

    public Vector2D(Vector2D inPoint) {
        mX = inPoint.x();
        mY = inPoint.y();
    }

    public Vector2D(PointF inPoint) {
        mX = inPoint.x;
        mY = inPoint.y;
    }

    public Vector2D(float mX, float mY) {
        this.mX = mX;
        this.mY = mY;
    }

    public Vector2D copy() {
        return new Vector2D(this);
    }

    public float x() {
        return this.mX;
    }

    public float y() {
        return this.mY;
    }

    public void set(float x, float y) {
        this.mX = x;
        this.mY = y;
    }

    public void set(Vector2D inVector) {
        this.mX = inVector.mX;
        this.mY = inVector.mY;
    }

    public float length() {
        return (float)Math.sqrt(lengthSq());
    }

    public float lengthSq() {
        return (this.mX * this.mX + this.mY * this.mY);
    }

    public Vector2D normalize() {
        float length = this.length();
        if(length != 0) {
            this.mX /= length;
            this.mY /= length;
        }
        return this;
    }

    public Vector2D negate() {
        this.mX *= -1.0f;
        this.mY *= -1.0f;
        return this;
    }

    public Vector2D add(float x, float y) {
        this.mX += x;
        this.mY += y;
        return this;
    }

    public Vector2D add(Vector2D inVec) {
        return add(inVec.x(), inVec.y());
    }

    public Vector2D subtract(float x, float y) {
        this.mX -= x;
        this.mY -= y;
        return this;
    }

    public Vector2D subtract(Vector2D inVec) {
        return subtract(inVec.x(), inVec.y());
    }

    public Vector2D scale(float inScalar) {
        this.mX *= inScalar;
        this.mY *= inScalar;
        return this;
    }

    public float distance(Vector2D inVec) {
        return (float)Math.sqrt(distanceSq(inVec));
    }

    public float distanceSq(Vector2D inVec) {
        float dx = this.mX - inVec.mX;
        float dy = this.mY - inVec.mY;
        return (dx * dx + dy * dy);
    }

    public float dotProduct(Vector2D inVec) {
        return this.mX * inVec.x() + this.mY * inVec.y();
    }

    public float angle() {
        return (float)Math.atan2(this.mY, this.mX);
    }

    public float angle(Vector2D inVec) {
        return (float)Math.atan2(this.mY - inVec.y(), this.mX - inVec.x());
    }

    public boolean isZero() {
        return ((this.mX == 0) && (this.mY == 0));
    }

    public Vector2D divide(float inScalarX, float inScalarY) {
        if(inScalarX == 0 || inScalarY == 0)
            return null;
        mX = mX /inScalarX;
        mY = mY /inScalarY;
        return this;
    }

    public Vector2D rotate(Vector2D inAnchor, float inRadians) {
        this.subtract(inAnchor);
        double s = Math.sin(inRadians);
        double c = Math.cos(inRadians);
        double tx = c * x() - s * y();
        double ty = s * x() + c * y();
        set((float)tx + x(), (float)ty + y());
        return this;
    }

    public Vector2D rotate(float radians) {
        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);

        float newX = this.mX * cos - this.mY * sin;
        float newY = this.mX * sin + this.mY * cos;

        this.mX = newX;
        this.mY = newY;
        return this;
    }

    @Override
    public String toString() {
        return "" + mX + " " + mY;
    }

    public PointF toPointF() {
        return new PointF(mX, mY);
    }

}
