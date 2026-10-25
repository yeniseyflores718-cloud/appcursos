<?php


header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$id_curso = isset($_GET['id_curso']) ? intval($_GET['id_curso']) : 0;

if ($id_curso <= 0) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode(["error" => "el ide del curso proporcionado no es valido"]);
    exit;
}

try {

    $stmt = $pdo->prepare("SELECT Cursos.id_curso, Cursos.codigo, Cursos.nombre, Cursos.descripcion, Cursos.espacio, Cursos.total_horas, Cursos.dias_atencion, Cursos.cupo_maximo, Cursos.url_imagen_principal, Carreras.nombre AS carrera FROM Cursos INNER JOIN Carreras ON Cursos.id_carrera = Carreras.id_carrera WHERE Cursos.id_curso = ?");
    $stmt->execute([$id_curso]);
    $curso = $stmt->fetch();

    if (!$curso) {
        header("HTTP/1.1 404 Not Found");
        echo json_encode(["error" => "no se encontro el curso"]);
        exit;
    }

    $stmt = $pdo->prepare("SELECT id, curso_id, url_imagen FROM imagenes_curso WHERE curso_id = ?");
    $stmt->execute([$id_curso]);
    $imagenes = $stmt->fetchAll();

    $curso['id_curso'] = (int)$curso['id_curso'];
    $curso['total_horas'] = $curso['total_horas'] !== null ? (int)$curso['total_horas'] : null;
    $curso['cupo_maximo'] = $curso['cupo_maximo'] !== null ? (int)$curso['cupo_maximo'] : null;

    foreach ($imagenes as &$img) {
        $img['id'] = (int)$img['id'];
        $img['curso_id'] = (int)$img['curso_id'];
    }
    unset($img);

    $curso['imagenes'] = $imagenes;
    echo json_encode($curso);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "hubo ub error al obtener el detalle del curso"]);
}
?>
