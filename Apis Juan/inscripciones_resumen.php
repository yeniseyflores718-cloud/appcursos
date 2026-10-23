<?php

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

try {
    
    $stmt = $pdo->query("SELECT Inscripciones.id_inscripcion, Inscripciones.fecha_registro, Alumnos.matricula, Alumnos.nombre_completo, Alumnos.correo_institucional, Cursos.id_curso, Cursos.codigo, Cursos.nombre AS curso, Cursos.cupo_maximo, Carreras.nombre AS carrera FROM Inscripciones INNER JOIN Alumnos ON Inscripciones.matricula = Alumnos.matricula INNER JOIN Cursos ON Inscripciones.id_curso = Cursos.id_curso LEFT JOIN Carreras ON Alumnos.id_carrera = Carreras.id_carrera ORDER BY Inscripciones.id_inscripcion DESC");

    $inscripciones = $stmt->fetchAll();

    foreach ($inscripciones as &$inscripcion) {
        $inscripcion['id_inscripcion'] = (int)$inscripcion['id_inscripcion'];
        $inscripcion['id_curso'] = (int)$inscripcion['id_curso'];
        $inscripcion['cupo_maximo'] = $inscripcion['cupo_maximo'] !== null ? (int)$inscripcion['cupo_maximo'] : null;
    }
    unset($inscripcion);


    echo json_encode($inscripciones);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "error al obtener las inscripciones"]);
}
?>
