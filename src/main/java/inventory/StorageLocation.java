package inventory;

sealed interface StorageLocation permits CorrosivesCabinet, StandardShelf {

}
record CorrosivesCabinet(boolean ventilated)    implements StorageLocation {}
record StandardShelf(String room, int position) implements StorageLocation {}
