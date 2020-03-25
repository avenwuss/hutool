package cn.hutool.core.collection;

import cn.hutool.core.comparator.PinyinComparator;
import cn.hutool.core.comparator.PropertyComparator;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PageUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListUtil {
	/**
	 * 新建一个空List
	 *
	 * @param <T>      集合元素类型
	 * @param isLinked 是否新建LinkedList
	 * @return List对象
	 * @since 4.1.2
	 */
	public static <T> List<T> list(boolean isLinked) {
		return isLinked ? new LinkedList<>() : new ArrayList<>();
	}

	/**
	 * 新建一个List
	 *
	 * @param <T>      集合元素类型
	 * @param isLinked 是否新建LinkedList
	 * @param values   数组
	 * @return List对象
	 * @since 4.1.2
	 */
	@SafeVarargs
	public static <T> List<T> list(boolean isLinked, T... values) {
		if (ArrayUtil.isEmpty(values)) {
			return list(isLinked);
		}
		final List<T> arrayList = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
		Collections.addAll(arrayList, values);
		return arrayList;
	}

	/**
	 * 新建一个List
	 *
	 * @param <T>        集合元素类型
	 * @param isLinked   是否新建LinkedList
	 * @param collection 集合
	 * @return List对象
	 * @since 4.1.2
	 */
	public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
		if (null == collection) {
			return list(isLinked);
		}
		return isLinked ? new LinkedList<>(collection) : new ArrayList<>(collection);
	}

	/**
	 * 新建一个List<br>
	 * 提供的参数为null时返回空{@link ArrayList}
	 *
	 * @param <T>      集合元素类型
	 * @param isLinked 是否新建LinkedList
	 * @param iterable {@link Iterable}
	 * @return List对象
	 * @since 4.1.2
	 */
	public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
		if (null == iterable) {
			return list(isLinked);
		}
		return list(isLinked, iterable.iterator());
	}

	/**
	 * 新建一个ArrayList<br>
	 * 提供的参数为null时返回空{@link ArrayList}
	 *
	 * @param <T>      集合元素类型
	 * @param isLinked 是否新建LinkedList
	 * @param iter     {@link Iterator}
	 * @return ArrayList对象
	 * @since 4.1.2
	 */
	public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
		final List<T> list = list(isLinked);
		if (null != iter) {
			while (iter.hasNext()) {
				list.add(iter.next());
			}
		}
		return list;
	}

	/**
	 * 新建一个List<br>
	 * 提供的参数为null时返回空{@link ArrayList}
	 *
	 * @param <T>        集合元素类型
	 * @param isLinked   是否新建LinkedList
	 * @param enumration {@link Enumeration}
	 * @return ArrayList对象
	 * @since 3.0.8
	 */
	public static <T> List<T> list(boolean isLinked, Enumeration<T> enumration) {
		final List<T> list = list(isLinked);
		if (null != enumration) {
			while (enumration.hasMoreElements()) {
				list.add(enumration.nextElement());
			}
		}
		return list;
	}

	/**
	 * 新建一个ArrayList
	 *
	 * @param <T>    集合元素类型
	 * @param values 数组
	 * @return ArrayList对象
	 */
	@SafeVarargs
	public static <T> ArrayList<T> toList(T... values) {
		return (ArrayList<T>) list(false, values);
	}

	/**
	 * 新建LinkedList
	 *
	 * @param values 数组
	 * @param <T>    类型
	 * @return LinkedList
	 * @since 4.1.2
	 */
	@SafeVarargs
	public static <T> LinkedList<T> toLinkedList(T... values) {
		return (LinkedList<T>) list(true, values);
	}

	/**
	 * 新建一个CopyOnWriteArrayList
	 *
	 * @param <T>        集合元素类型
	 * @param collection 集合
	 * @return {@link CopyOnWriteArrayList}
	 */
	public static <T> CopyOnWriteArrayList<T> toCopyOnWriteArrayList(Collection<T> collection) {
		return (null == collection) ? (new CopyOnWriteArrayList<>()) : (new CopyOnWriteArrayList<>(collection));
	}

	/**
	 * 新建一个ArrayList
	 *
	 * @param <T>        集合元素类型
	 * @param collection 集合
	 * @return ArrayList对象
	 */
	public static <T> ArrayList<T> toList(Collection<T> collection) {
		return (ArrayList<T>) list(false, collection);
	}

	/**
	 * 新建一个ArrayList<br>
	 * 提供的参数为null时返回空{@link ArrayList}
	 *
	 * @param <T>      集合元素类型
	 * @param iterable {@link Iterable}
	 * @return ArrayList对象
	 * @since 3.1.0
	 */
	public static <T> ArrayList<T> toList(Iterable<T> iterable) {
		return (ArrayList<T>) list(false, iterable);
	}

	/**
	 * 新建一个ArrayList<br>
	 * 提供的参数为null时返回空{@link ArrayList}
	 *
	 * @param <T>      集合元素类型
	 * @param iterator {@link Iterator}
	 * @return ArrayList对象
	 * @since 3.0.8
	 */
	public static <T> ArrayList<T> toList(Iterator<T> iterator) {
		return (ArrayList<T>) list(false, iterator);
	}

	/**
	 * 新建一个ArrayList<br>
	 * 提供的参数为null时返回空{@link ArrayList}
	 *
	 * @param <T>         集合元素类型
	 * @param enumeration {@link Enumeration}
	 * @return ArrayList对象
	 * @since 3.0.8
	 */
	public static <T> ArrayList<T> toList(Enumeration<T> enumeration) {
		return (ArrayList<T>) list(false, enumeration);
	}

	/**
	 * 对指定List分页取值
	 *
	 * @param <T>      集合元素类型
	 * @param pageNo   页码，从0开始计数，0表示第一页
	 * @param pageSize 每页的条目数
	 * @param list     列表
	 * @return 分页后的段落内容
	 * @since 4.1.20
	 */
	public static <T> List<T> page(int pageNo, int pageSize, List<T> list) {
		if (CollUtil.isEmpty(list)) {
			return new ArrayList<>(0);
		}

		int resultSize = list.size();
		// 每页条目数大于总数直接返回所有
		if (resultSize <= pageSize) {
			if (pageNo < 1) {
				return Collections.unmodifiableList(list);
			} else {
				// 越界直接返回空
				return new ArrayList<>(0);
			}
		}
		final int[] startEnd = PageUtil.transToStartEnd(pageNo, pageSize);
		if (startEnd[1] > resultSize) {
			startEnd[1] = resultSize;
		}
		return list.subList(startEnd[0], startEnd[1]);
	}

	/**
	 * 针对List排序，排序会修改原List
	 *
	 * @param <T>  元素类型
	 * @param list 被排序的List
	 * @param c    {@link Comparator}
	 * @return 原list
	 * @see Collections#sort(List, Comparator)
	 */
	public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
		list.sort(c);
		return list;
	}

	/**
	 * 根据Bean的属性排序
	 *
	 * @param <T>      元素类型
	 * @param list     List
	 * @param property 属性名
	 * @return 排序后的List
	 * @since 4.0.6
	 */
	public static <T> List<T> sortByProperty(List<T> list, String property) {
		return sort(list, new PropertyComparator<>(property));
	}

	/**
	 * 根据汉字的拼音顺序排序
	 *
	 * @param list List
	 * @return 排序后的List
	 * @since 4.0.8
	 */
	public static List<String> sortByPinyin(List<String> list) {
		return sort(list, new PinyinComparator());
	}

	/**
	 * 反序给定List，会在原List基础上直接修改
	 *
	 * @param <T>  元素类型
	 * @param list 被反转的List
	 * @return 反转后的List
	 * @since 4.0.6
	 */
	public static <T> List<T> reverse(List<T> list) {
		Collections.reverse(list);
		return list;
	}

	/**
	 * 反序给定List，会创建一个新的List，原List数据不变
	 *
	 * @param <T>  元素类型
	 * @param list 被反转的List
	 * @return 反转后的List
	 * @since 4.0.6
	 */
	public static <T> List<T> reverseNew(List<T> list) {
		final List<T> list2 = ObjectUtil.clone(list);
		return reverse(list2);
	}

	/**
	 * 设置或增加元素。当index小于List的长度时，替换指定位置的值，否则在尾部追加
	 *
	 * @param <T>     元素类型
	 * @param list    List列表
	 * @param index   位置
	 * @param element 新元素
	 * @return 原List
	 * @since 4.1.2
	 */
	public static <T> List<T> setOrAppend(List<T> list, int index, T element) {
		if (index < list.size()) {
			list.set(index, element);
		} else {
			list.add(element);
		}
		return list;
	}

	/**
	 * 截取集合的部分
	 *
	 * @param <T>   集合元素类型
	 * @param list  被截取的数组
	 * @param start 开始位置（包含）
	 * @param end   结束位置（不包含）
	 * @return 截取后的数组，当开始位置超过最大时，返回空的List
	 */
	public static <T> List<T> sub(List<T> list, int start, int end) {
		return sub(list, start, end, 1);
	}

	/**
	 * 截取集合的部分
	 *
	 * @param <T>   集合元素类型
	 * @param list  被截取的数组
	 * @param start 开始位置（包含）
	 * @param end   结束位置（不包含）
	 * @param step  步进
	 * @return 截取后的数组，当开始位置超过最大时，返回空的List
	 * @since 4.0.6
	 */
	public static <T> List<T> sub(List<T> list, int start, int end, int step) {
		if (list == null) {
			return null;
		}

		if (list.isEmpty()) {
			return new ArrayList<>(0);
		}

		final int size = list.size();
		if (start < 0) {
			start += size;
		}
		if (end < 0) {
			end += size;
		}
		if (start == size) {
			return new ArrayList<>(0);
		}
		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}
		if (end > size) {
			if (start >= size) {
				return new ArrayList<>(0);
			}
			end = size;
		}

		if (step <= 1) {
			return list.subList(start, end);
		}

		final List<T> result = new ArrayList<>();
		for (int i = start; i < end; i += step) {
			result.add(list.get(i));
		}
		return result;
	}

	/**
	 * 编辑列表，此方法会修改原列表的内容<br>
	 * 编辑过程通过传入的Editor实现编辑列表中元素内容，这个Editor实现可以实现以下功能：
	 *
	 * <pre>
	 *  1、修改元素对象，返回集合中为修改后的对象
	 * </pre>
	 *
	 * @param <T>    集合元素类型
	 * @param list   集合
	 * @param editor 编辑器接口
	 * @return 编辑后的数组
	 * @since 4.1.8
	 */
	public static <T> List<T> edit(List<T> list, Editor<T> editor) {
		if (null == list || null == editor) {
			return list;
		}

		for (T t : list) {
			editor.edit(t);
		}

		return list;
	}

	/**
	 * 过滤<br>
	 * 过滤过程通过传入的Editor实现来返回需要的元素内容，这个Editor实现可以实现以下功能：
	 *
	 * <pre>
	 * 1、过滤出需要的对象，如果返回null表示这个元素对象抛弃
	 * 2、修改元素对象，返回集合中为修改后的对象
	 * </pre>
	 *
	 * @param <T>    集合元素类型
	 * @param list   集合
	 * @param editor 编辑器接口
	 * @return 过滤后的数组
	 * @since 4.1.8
	 */
	public static <T> List<T> filter(List<T> list, Editor<T> editor) {
		if (null == list || null == editor) {
			return list;
		}

		final List<T> list2 = (list instanceof LinkedList) ? new LinkedList<>() : new ArrayList<>(list.size());
		T modified;
		for (T t : list) {
			modified = editor.edit(t);
			if (null != modified) {
				list2.add(modified);
			}
		}
		return list2;
	}
}
