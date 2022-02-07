import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int n = 0; n < storage.length; n++){
            if (storage[n] == null){
                storage[n] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume r : storage) {
            if (r == null){
                break;
            }
            if (uuid.equals(r.uuid)) {
                return r;
            }

        } return null;
    }

    void delete(String uuid) {
        for (int n = 0; n < storage.length; n++){
            if (storage[n] == null){
                break;
            }else if (uuid.equals(storage[n].uuid)) {
                if (storage[n + 1] == null){
                    storage[n] = null;
                }else{
                    if (storage.length - 1 - n >= 0)
                        System.arraycopy(storage, n + 1, storage, n, storage.length - 1 - n);
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int i = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            } else {
                i++;
            }
        }

        return Arrays.copyOfRange(storage, 0, i);
    }

    int size() {
        int i = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            } else {
                i++;
            }
        }

        return i;
    }
}
