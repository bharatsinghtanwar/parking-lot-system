-- Seed data for Parking Lot System
-- Assumes Spring Boot default physical naming strategy (CamelCase -> snake_case)

INSERT INTO parking_lot (id, name, address) VALUES (1, 'Central Lot', '123 Main St');

INSERT INTO parking_floor (id, floor_number, parking_lot_id) VALUES (1, 0, 1);
INSERT INTO parking_floor (id, floor_number, parking_lot_id) VALUES (2, 1, 1);

INSERT INTO parking_spot (id, spot_id, spot_type, occupied, parking_floor_id) VALUES (1, 'F0-S1', 'COMPACT', FALSE, 1);
INSERT INTO parking_spot (id, spot_id, spot_type, occupied, parking_floor_id) VALUES (2, 'F0-S2', 'LARGE', FALSE, 1);
INSERT INTO parking_spot (id, spot_id, spot_type, occupied, parking_floor_id) VALUES (3, 'F1-S1', 'COMPACT', TRUE, 2);

INSERT INTO vehicle (id, license_plate, vehicle_type) VALUES (1, 'ABC123', 'CAR');
INSERT INTO vehicle (id, license_plate, vehicle_type) VALUES (2, 'XYZ789', 'BIKE');

-- Example open ticket could be added at runtime via API
-- Endpoint sequence example:
-- POST /api/tickets/open?spotId=1&vehicleId=1
