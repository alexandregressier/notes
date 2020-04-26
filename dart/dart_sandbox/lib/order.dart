import 'package:uuid_type/uuid_type.dart';

class Order {
  Uuid id;
  String reference;
  DateTime creationDateTime;

  Order(this.reference) {
    id = Uuid.parse(uuid.v4());
    creationDateTime = DateTime.now();
  }
}
