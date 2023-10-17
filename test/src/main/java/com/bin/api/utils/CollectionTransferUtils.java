package com.bin.api.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class CollectionTransferUtils {

    public static <K, V> Map<K, V> toMap(Iterable<V> sourceList, Function<V, K> IMapKey) {
        Map<K, V> result = Maps.newHashMap();
        if (sourceList == null) {
            return result;
        }
        sourceList.forEach(t -> {
            K k = IMapKey.apply(t);
            if (k != null) {
                result.put(k, t);
            }
        });
        return result;
    }

    public static <V, K> List<K> toList(Iterable<V> sourceList, Function<V, K> IMapKey) {
        Set<K> result = Sets.newHashSet();
        if (sourceList == null) {
            return Lists.newArrayList();
        } else {
            sourceList.forEach(t -> {
                if (t != null) {
                    K k = IMapKey.apply(t);
                    if (k != null) {
                        result.add(k);
                    }
                }
            });
        }
        return Lists.newArrayList(result);
    }

    public static <V, K> Set<K> toSet(Iterable<V> sourceList, Function<V, K> IMapKey) {
        Set<K> result = Sets.newHashSet();
        if (sourceList == null) {
            return result;
        } else {
            sourceList.forEach(t -> {
                if (t != null) {
                    K k = IMapKey.apply(t);
                    if (k != null) {
                        result.add(k);
                    }
                }
            });
        }
        return result;
    }

    public static <K> Set<K> toSet(Iterable<K> sourceList) {
        Set<K> result = Sets.newHashSet();
        if (sourceList == null) {
            return result;
        } else {
            sourceList.forEach(t -> {
                result.add(t);
            });
        }
        return result;
    }

    public static <K, V> Map<K, List<V>> toMapList(Iterable<V> sourceList, Function<V, K> IMapKey) {
        Map<K, List<V>> result = Maps.newHashMap();
        if (sourceList == null) {
            return result;
        }
        sourceList.forEach(v -> {
            K key = IMapKey.apply(v);
            if (key != null) {
                if (result.containsKey(key)) {
                    result.get(key).add(v);
                } else {
                    List<V> list = Lists.newArrayList();
                    list.add(v);
                    result.put(key, list);
                }
            }
        });
        return result;
    }

    public static <L, K, V> Map<K, Set<V>> toMapSet(Iterable<L> sourceList, Function<L, K> MapKey, Function<L, V> MapValue) {
        Map<K, Set<V>> result = Maps.newHashMap();
        if (sourceList == null) {
            return result;
        }
        sourceList.forEach(v -> {
            K key = MapKey.apply(v);
            if (key != null) {
                if (result.containsKey(key)) {
                    result.get(key).add(MapValue.apply(v));
                } else {
                    Set<V> set = Sets.newHashSet();
                    set.add(MapValue.apply(v));
                    result.put(key, set);
                }
            }
        });
        return result;
    }


    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        Set<T> result = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(list1)) {
            result.addAll(list1);
        }
        if (!CollectionUtils.isEmpty(list2)) {
            result.addAll(list2);
        }
        return Lists.newArrayList(result);
    }


    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return Lists.newArrayList();
    }
}
