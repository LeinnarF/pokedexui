package org.nice.lib.listview;

import java.util.Optional;

public record Item<T>(T item, Optional<String> addOptions) {
}
