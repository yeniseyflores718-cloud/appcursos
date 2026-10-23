<?php
// obtener_cursos.php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$carrera_id = isset($_GET['carrera_id']) ? intval($_GET['carrera_id']) : 0;

try {

    if ($carrera_id > 0) {
        $stmt = $pdo->prepare("SELECT id_curso, id_carrera, codigo, nombre, espacio, url_imagen_principal FROM Cursos WHERE id_carrera = ?");
        $stmt->execute([$carrera_id]);
    } else {
        $stmt = $pdo->query("SELECT id_curso, id_carrera, codigo, nombre, espacio, url_imagen_principal FROM Cursos");
    }

    $cursos = $stmt->fetchAll();
    
    foreach ($cursos as &$c) {
        $c['id_curso'] = (int)$c['id_curso'];
        $c['id_carrera'] = $c['id_carrera'] !== null ? (int)$c['id_carrera'] : null;
    }

    echo json_encode($cursos);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener catálogo de cursos: " . $e->getMessage()]);
}
?>