
<?php

header("Content-Type: application/json; charset=utf-8");

require_once "config/conexion.php";

try {
    $stmt = $pdo->query("SELECT id_carrera, nombre, logo FROM Carreras ORDER BY nombre ASC");
    $carreras = $stmt->fetchAll();
    echo json_encode($carreras, JSON_UNESCAPED_UNICODE);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener las carreras: " . $e->getMessage()]);
}
?>
