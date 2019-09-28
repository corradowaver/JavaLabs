import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class MyStringBuilder {
  private StringBuilder sb = new StringBuilder();
  private Deque<Action> actions = new LinkedList<>();

  public MyStringBuilder() {
    sb = new StringBuilder();
  }

  public MyStringBuilder(int capacity) {
    sb = new StringBuilder(capacity);
  }

  public MyStringBuilder(CharSequence seq) {
    sb = new StringBuilder(seq);
  }

  public MyStringBuilder(String str) {
    sb = new StringBuilder(str);
  }

  public void undo() {
    if (!actions.isEmpty()) {
      actions.pop().undo();
    }
  }

  public String toString() {
    return sb.toString();
  }

  public MyStringBuilder append​(boolean b) {
    sb.append(b);
    actions.push(() -> sb.delete(sb.length() - Boolean.toString(b).length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(char c) {
    sb.append(c);
    actions.push(() -> sb.delete(sb.length() - 1, sb.length()));
    return this;
  }

  public MyStringBuilder append​(char[] str) {
    sb.append(str);
    actions.push(() -> sb.delete(sb.length() - str.length, sb.length()));
    return this;
  }

  public MyStringBuilder append​(char[] str, int offset, int len) {
    sb.append(str);
    actions.push(() -> sb.delete(sb.length() - len, sb.length()));
    return this;
  }

  public MyStringBuilder append​(double d) {
    sb.append(d);
    actions.push(() -> sb.delete(sb.length() - Double.toString(d).length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(float f) {
    sb.append(f);
    actions.push(() -> sb.delete(sb.length() - Float.toString(f).length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(int i) {
    sb.append(i);
    actions.push(() -> sb.delete(sb.length() - Integer.toString(i).length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(long lng) {
    sb.append(lng);
    actions.push(() -> sb.delete(sb.length() - Long.toString(lng).length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(CharSequence s) {
    sb.append(s);
    actions.push(() -> sb.delete(sb.length() - s.length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(CharSequence s, int start, int end) {
    sb.append(s);
    actions.push(() -> sb.delete(sb.length() - s.length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(Object obj) {
    sb.append(obj);
    actions.push(() -> sb.delete(sb.length() - obj.toString().length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(String str) {
    sb.append(str);
    actions.push(() -> sb.delete(sb.length() - str.length(), sb.length()));
    return this;
  }

  public MyStringBuilder append​(StringBuffer sb) {
    this.sb.append(sb);
    actions.push(() -> this.sb.delete(sb.length() - sb.toString().length(), sb.length()));
    return this;
  }

  public MyStringBuilder appendCodePoint​(int codePoint) {
    sb.appendCodePoint(codePoint);
    actions.push(() -> sb.delete(sb.length() - String.valueOf(codePoint).length(), sb.length()));
    return this;
  }

  public int capacity() {
    return sb.capacity();
  }

  public char charAt​(int index) {
    return sb.charAt(index);
  }

  public IntStream chars() {
    return sb.chars();
  }

  public int codePointAt​(int index) {
    return sb.codePointAt(index);
  }

  public int codePointBefore​(int index) {
    return sb.codePointBefore(index);
  }

  public int codePointCount​(int beginIndex, int endIndex) {
    return sb.codePointCount(beginIndex, endIndex);
  }

  public IntStream codePoints() {
    return sb.codePoints();
  }

  public int compareTo​(StringBuilder another) {
    return sb.compareTo(another);
  }

  public MyStringBuilder delete​(int start, int end) {
    String deleted = sb.substring(start, end);
    sb.delete(start, end);
    actions.push(() -> sb.insert(start, deleted));
    return this;
  }

  public MyStringBuilder deleteCharAt​(int index) {
    char deleted = sb.charAt(index);
    sb.deleteCharAt(index);
    actions.push(() -> sb.insert(index, deleted));
    return this;
  }

  public void ensureCapacity​(int minimumCapacity) {
    sb.ensureCapacity(minimumCapacity);
  }

  public void getChars​(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
    sb.getChars(srcBegin, srcEnd, dst, dstBegin);
  }

  public int indexOf​(String str) {
    return sb.indexOf(str);
  }

  public int indexOf​(String str, int fromIndex) {
    return sb.indexOf(str, fromIndex);
  }

  public MyStringBuilder insert​(int offset, boolean b) {
    sb.insert(offset, b);
    actions.push(() -> sb.delete(offset, Boolean.toString(b).length()));
    return this;
  }

  public MyStringBuilder insert​(int offset, char c) {
    sb.insert(offset, c);
    actions.push(() -> sb.delete(offset, 1));
    return this;
  }

  public MyStringBuilder insert​(int offset, char[] str) {
    sb.insert(offset, str);
    actions.push(() -> sb.delete(offset, str.length));
    return this;
  }

  public MyStringBuilder insert​(int index, char[] str, int offset, int len) {
    sb.insert(index, str, offset, len);
    actions.push(() -> sb.delete(index, len));
    return this;
  }

  public MyStringBuilder insert​(int offset, double d) {
    sb.insert(offset, d);
    actions.push(() -> sb.delete(offset, Double.toString(d).length()));
    return this;
  }

  public MyStringBuilder insert​(int offset, float f) {
    sb.insert(offset, f);
    actions.push(() -> sb.delete(offset, Float.toString(f).length()));
    return this;
  }

  public MyStringBuilder insert​(int offset, int i) {
    sb.insert(offset, i);
    actions.push(() -> sb.delete(offset, Integer.toString(i).length()));
    return this;
  }

  public MyStringBuilder insert​(int offset, long l) {
    sb.insert(offset, l);
    actions.push(() -> sb.delete(offset, Long.toString(l).length()));
    return this;
  }

  public MyStringBuilder insert​(int dstOffset, CharSequence s) {
    sb.insert(dstOffset, s);
    actions.push(() -> sb.delete(dstOffset, s.length()));
    return this;
  }

  public MyStringBuilder insert​(int dstOffset, CharSequence s, int start, int end) {
    sb.append(s);
    actions.push(() -> sb.delete(sb.length() - (start - end), sb.length()));
    return this;
  }

  public MyStringBuilder insert​(int offset, Object obj) {
    sb.insert(offset, obj);
    actions.push(() -> sb.delete(offset, obj.toString().length()));
    return this;
  }

  public MyStringBuilder insert​(int offset, String str) {
    sb.insert(offset, str);
    actions.push(() -> sb.delete(offset, str.length()));
    return this;
  }

  public int lastIndexOf​(String str) {
    return sb.lastIndexOf(str);
  }

  public int lastIndexOf​(String str, int fromIndex) {
    return sb.lastIndexOf(str, fromIndex);
  }

  public int length() {
    return sb.length();
  }

  public int offsetByCodePoints​(int index, int codePointOffset) {
    return sb.offsetByCodePoints(index, codePointOffset);
  }

  public MyStringBuilder replace​(int start, int end, String str) {
    String deleted = sb.substring(start, end);
    sb.replace(start, end, str);
    actions.push(() -> sb.replace(start, end, deleted));
    return this;
  }

  public MyStringBuilder reverse() {
    sb.reverse();
    actions.push(() -> sb.reverse());
    return this;
  }

  public void setCharAt​(int index, char ch) {
    char deleted = sb.toString().charAt(index);
    sb.setCharAt(index, ch);
    actions.push(() -> sb.setCharAt(index, deleted));
  }

  public void setLength​(int newLength) {
    sb.setLength(newLength);
  }

  public CharSequence subSequence​(int start, int end) {
    return sb.subSequence(start, end);
  }

  public String substring​(int start) {
    return sb.substring(start);
  }

  public String substring​(int start, int end) {
    return sb.substring(start, end);
  }

  public void trimToSize() {
    sb.trimToSize();
  }


}
