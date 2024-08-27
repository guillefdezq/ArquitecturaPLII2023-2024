del ..\test\*.ens
$Files = ls ..\test\*.ces
clear
$i = 0
foreach ($File in $Files) {
  $Host.UI.RawUI.WindowTitle = 'ANT: ' + $i + "/" + $($Files.Count) + "=" + [math]::round(($i / $Files.Count) * 100) + "%"
  #Write-Progress -Activity "Pasando casos de prueba ANT para Arquitectura: $($File.name)" -Status "File $File of $($Files.Count)" -PercentComplete (($i / $Files.Count) * 100)
  $i++
  ant finalTest -Dtest-file-name="$($File.Name)"
}