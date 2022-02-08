import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    protected int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        for (int n = 0; n < storage.length; n++) {
            if (storage[n] == null) {
                storage[n] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r == null) {
                break;
            } else if (uuid.equals(r.uuid)) {
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int n = 0; n < storage.length; n++) {
            if (storage[n] == null) {
                break;
            } else if (uuid.equals(storage[n].uuid)) {
                if (storage[n + 1] == null) {
                    storage[n] = null;
                } else {
                    for (int m = n; storage[m] != null; m++) {
                        storage[m] = storage[m + 1];
                    }
                }
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
