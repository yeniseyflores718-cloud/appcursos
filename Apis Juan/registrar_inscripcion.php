<?php

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$matricula = isset($_POST['matricula']) ? trim($_POST['matricula']) : "";
$id_curso = isset($_POST['id_curso']) ? intval($_POST['id_curso']) : 0;
$fecha_registro = isset($_POST['fecha_registro']) ? $_POST['fecha_registro'] : "";

if ($matricula == "" || $id_curso <= 0 || $fecha_registro == "") {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode(["error" => "debe tener todos los campos"]);
    exit;
}

try {

    $stmt = $pdo->prepare("SELECT matricula FROM Alumnos WHERE matricula = ?");
    $stmt->execute([$matricula]);
    $alumno = $stmt->fetch();

    if (!$alumno) {
        header("HTTP/1.1 404 Not Found");
        echo json_encode(["error" => "no se encontro al alumno"]);
        exit;
    }


    $stmt = $pdo->prepare("SELECT id_curso, cupo_maximo FROM Cursos WHERE id_curso = ?");
    $stmt->execute([$id_curso]);
    $curso = $stmt->fetch();

    if (!$curso) {
        header("HTTP/1.1 404 Not Found");
        echo json_encode(["error" => "no se encontro el curso"]);
        exit;
    }


    $stmt = $pdo->prepare("SELECT COUNT(*) AS total FROM Inscripciones WHERE id_curso = ?");
    $stmt->execute([$id_curso]);
    $resultado = $stmt->fetch();

    if ($curso['cupo_maximo'] !== null && $resultado['total'] >= $curso['cupo_maximo']) {
        header("HTTP/1.1 409 Conflict");
        echo json_encode(["error" => "el curso ya lleno todos sus lugares"]);
        exit;
    }

    $stmt = $pdo->prepare("INSERT INTO Inscripciones (id_curso, matricula, fecha_registro) VALUES (?, ?, ?)");
    $stmt->execute([$id_curso, $matricula, $fecha_registro]);

    echo json_encode([
        "mensaje" => "la inscripcion se registro correctam",
        "id_inscripcion" => (int)$pdo->lastInsertId()
    ]);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "erroa al querer registrar la incripcion"]);
}
?>
