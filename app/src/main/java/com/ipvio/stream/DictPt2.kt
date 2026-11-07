package com.ipvio.stream

// Traduções em português (parte 2): Ajustes (categorias e opções), diagnóstico, playlists, aparência.
val DictPt2 = """
Keine Auswahl verfügbar (feste Qualität).¦Nenhuma opção disponível (qualidade fixa).
Sprungweite¦Tamanho do salto
Gilt für Links/Rechts, Halten und die Zeitleiste.¦Vale para Esquerda/Direita, segurar e a linha do tempo.
Nächste Folge automatisch¦Próximo episódio automático
Endzeit anzeigen¦Mostrar hora de término
Startet in {0}¦Começa em {0}
Server antwortet mit Fehler {#0}¦O servidor respondeu com erro {#0}
Der Server hat keine Daten geliefert¦O servidor não enviou dados
Keine Sender in der Playlist gefunden¦Nenhum canal encontrado na playlist
Unbekannter Fehler¦Erro desconhecido
Allgemein¦Geral
Start, Beenden, Autostart¦Início, saída, início automático
Sprache & Region¦Idioma e região
Sprache, Uhrzeit, Zeitzone¦Idioma, hora, fuso horário
Kartengröße, Raster¦Tamanho dos cards, grade
Design¦Aparência
Farben, Schrift, Animationen¦Cores, fonte, animações
Reihenfolge und Einträge¦Ordem e itens
Player¦Player
Spulen, Intro, Bildformat¦Avançar, abertura, formato de imagem
Ton & Untertitel¦Áudio e legendas
Sprachen, Untertitel-Stil¦Idiomas, estilo das legendas
Genres & Kategorien¦Gêneros e categorias
Automatisch oder vom Anbieter¦Automático ou do provedor
Live-TV & Guide¦TV ao vivo e guia
TV-Programm, Favoriten¦Programação, favoritos
Playlists¦Playlists
Verwalten, Aktualisieren¦Gerenciar, atualizar
Jugendschutz¦Controle dos pais
PIN, Inhalte ab 18¦PIN, conteúdo +18
Speicher¦Armazenamento
Cache, Verlauf, Fortsetzen¦Cache, histórico, continuar
Diagnose¦Diagnóstico
Status und Fehlersuche¦Status e solução de problemas
Über IPVIO Stream¦Sobre o AI Stream TV
Version, Quellen, Zurücksetzen¦Versão, fontes, redefinir
Wirklich? OK zum Bestätigen¦Tem certeza? OK para confirmar
‹ Zurück¦‹ Voltar
Automatisch (Gerät)¦Automático (aparelho)
Sprache der App¦Idioma do app
Alle Menüs und Hinweise erscheinen in dieser Sprache. Beschreibungen, Besetzung und Titel-Infos kommen von TMDB und werden in derselben Sprache geladen, sobald du einen Titel öffnest. Namen von Sendern, Filmen und Kategorien stammen von deinem Anbieter und bleiben unverändert.¦Todos os menus e avisos aparecem neste idioma. Descrições, elenco e informações do título vêm do TMDB e são carregados no mesmo idioma quando você abre um título. Nomes de canais, filmes e categorias vêm do seu provedor e não mudam.
Uhrzeit¦Hora
Zeitformat¦Formato de hora
24 Stunden (14:30)¦24 horas (14:30)
12 Stunden (2:30 PM)¦12 horas (2:30 PM)
Zeitzone¦Fuso horário
Systemzeit (Gerät: {0})¦Hora do sistema (aparelho: {0})
Uhrzeit jetzt¦Hora agora
Gilt für alle Uhrzeiten, zum Beispiel im TV-Guide und in der Live-TV-Infoleiste. „Systemzeit“ nutzt die Einstellung deines Geräts. Zeigt die App Uhrzeiten falsch an, steht dein Fernseher vermutlich auf UTC; wähle dann hier deine Zeitzone.¦Vale para todos os horários, por exemplo no guia de TV e na barra de informações da TV ao vivo. „Hora do sistema“ usa a configuração do seu aparelho. Se o app mostrar horários errados, sua TV provavelmente está em UTC; então escolha aqui o seu fuso horário.
PIN eingeben¦Digite o PIN
Die Jugendschutz-Einstellungen sind mit einer PIN geschützt.¦As configurações de controle dos pais são protegidas por um PIN.
Falsche PIN¦PIN incorreto
Neue PIN festlegen¦Definir novo PIN
Wähle eine 4-stellige PIN.¦Escolha um PIN de 4 dígitos.
PIN bestätigen¦Confirmar PIN
Gib die PIN noch einmal ein.¦Digite o PIN de novo.
Die PINs stimmen nicht überein¦Os PINs não coincidem
Inhalte für Erwachsene ausblenden¦Ocultar conteúdo adulto
Blendet Kategorien und Titel mit Begriffen wie XXX, Adult oder 18+ in der ganzen App aus: Startseite, Suche, Listen und Live-TV.¦Oculta categorias e títulos com termos como XXX, Adult ou 18+ em todo o app: tela inicial, busca, listas e TV ao vivo.
Der Schutz ist aktiv.¦A proteção está ativa.
{#0} Einträge in deiner Playlist würden ausgeblendet.¦{#0} itens da sua playlist seriam ocultados.
Kein Erwachsenen-Inhalt erkannt.¦Nenhum conteúdo adulto detectado.
PIN¦PIN
PIN ist gesetzt¦PIN definido
Keine PIN gesetzt¦Nenhum PIN definido
PIN ändern¦Alterar PIN
PIN festlegen¦Definir PIN
PIN entfernen¦Remover PIN
Mit einer PIN lässt sich der Schutz nur nach Eingabe der PIN ändern. Die Erkennung arbeitet mit Stichwörtern in Kategorie- und Titelnamen und ist kein vollständiger Filter. Beim Zurücksetzen aller Einstellungen bleibt die PIN erhalten.¦Com um PIN, a proteção só pode ser alterada após digitar o PIN. A detecção usa palavras-chave nos nomes de categorias e títulos e não é um filtro completo. Ao redefinir todas as configurações, o PIN é mantido.
Start¦Início
Beim Einschalten des Geräts öffnen¦Abrir ao ligar o aparelho
IPVIO Stream startet automatisch, sobald der Fernseher oder die Box hochgefahren ist.¦O AI Stream TV inicia automaticamente assim que a TV ou o box liga.
Berechtigung erteilt¦Permissão concedida
Berechtigung „Über anderen Apps einblenden“ fehlt¦Falta a permissão „Sobrepor a outros apps“
Einstellung öffnen¦Abrir configuração
Prüfen¦Verificar
Ab Android 10 erlaubt das System den Autostart nur mit dieser Berechtigung. Manche Geräte (z. B. einzelne Fire-TV-Modelle) blockieren den Autostart trotzdem; das kann die App nicht ändern.¦A partir do Android 10, o sistema só permite o início automático com essa permissão. Alguns aparelhos (ex.: certos modelos de Fire TV) bloqueiam o início automático mesmo assim; o app não pode mudar isso.
Suche¦Busca
Startseite¦Tela inicial
Diese Seite öffnet sich beim Start der App. Versteckte Menüpunkte sind nicht wählbar.¦Esta tela abre ao iniciar o app. Itens de menu ocultos não podem ser escolhidos.
Letzten Sender beim Start abspielen¦Tocar o último canal ao iniciar
Wenn du zuletzt Live TV gesehen hast, läuft dieser Sender direkt nach dem Start.¦Se você assistiu TV ao vivo por último, esse canal toca logo após iniciar.
Bedienung¦Controles
Beenden bestätigen¦Confirmar saída
Auf der Startseite muss „Zurück“ zweimal gedrückt werden, damit die App nicht aus Versehen schließt.¦Na tela inicial, é preciso apertar „Voltar“ duas vezes para o app não fechar sem querer.
Dieses Gerät bietet die Einstellung nicht an. Autostart funktioniert evtl. trotzdem.¦Este aparelho não oferece essa configuração. O início automático pode funcionar mesmo assim.
Zwischenspeicher¦Cache
Leeren¦Limpar
Gesamt¦Total
Geleerte Daten lädt die App bei Bedarf neu. Titel-Infos und Bilder erscheinen danach kurz später. Der Playlist-Zwischenspeicher macht den Start schnell; ohne ihn wartet die App auf den Download.¦Os dados limpos o app recarrega quando precisa. Informações e imagens aparecem um pouco depois. O cache da playlist deixa o início rápido; sem ele, o app espera o download.
Verlauf¦Histórico
Suchverlauf löschen¦Limpar histórico de buscas
Zuletzt geschaut löschen¦Limpar assistidos recentemente
Weiterschauen löschen¦Limpar continuar assistindo
Meine Liste und Favoriten bleiben immer erhalten.¦Minha lista e favoritos são sempre mantidos.
Filme, Serien und Sender¦Filmes, séries e canais
Raster zeigt viele Titel auf einmal. Kino zeigt oben die Details zum markierten Titel und unten eine Reihe zum Durchblättern.¦A grade mostra muitos títulos de uma vez. O Cinema mostra em cima os detalhes do título em foco e embaixo uma fileira para navegar.
Spalten (Raster)¦Colunas (grade)
Zeilen (Raster)¦Linhas (grade)
Banner wechselt¦Banner alterna
nie¦nunca
alle {#0} s¦a cada {#0} s
Kartengröße (Reihen)¦Tamanho dos cards (fileiras)
Kategorien-Ansicht¦Exibição de categorias
Liste mit Vorschau¦Lista com prévia
Das Raster kannst du zusätzlich direkt in der Rasteransicht mit dem Raster-Knopf ändern.¦A grade você também pode ajustar direto na exibição em grade, com o botão de grade.
Verschiebe Einträge mit den Pfeil-Knöpfen oder blende sie aus. Start und Einstellungen bleiben immer sichtbar.¦Mova os itens com os botões de seta ou oculte-os. Início e Ajustes ficam sempre visíveis.
versteckt¦oculto
sichtbar¦visível
Standard-Reihenfolge wiederherstellen¦Restaurar ordem padrão
Sprung links/rechts¦Salto esquerda/direita
Standard-Bildformat¦Formato de imagem padrão
Intro überspringen anbieten¦Oferecer pular abertura
Zeigt bei Serien einen Knopf „Intro überspringen“, wenn Intro-Zeiten bekannt sind.¦Mostra nas séries um botão „Pular abertura“ quando os tempos de abertura são conhecidos.
Stream-Info am Start¦Info do stream ao iniciar
Zeigt kurz Auflösung, Ton und Format beim Abspielen von Filmen und Serien.¦Mostra rapidamente resolução, áudio e formato ao reproduzir filmes e séries.
Steuerleiste ausblenden nach¦Ocultar barra de controle após
Startet die nächste Folge nach dem Abspann von selbst.¦Inicia o próximo episódio sozinho após os créditos.
Zeigt, um wie viel Uhr der Film voraussichtlich endet.¦Mostra a que horas o filme deve terminar.
Intro automatisch überspringen¦Pular abertura automaticamente
Überspringt bekannte Intros ohne Rückfrage.¦Pula aberturas conhecidas sem perguntar.
Farbthema¦Tema de cor
Eigene Farbe¦Cor personalizada
Übernehmen¦Aplicar
Ungültig¦Inválido
Wähle ein Farbfeld oder gib einen Hex-Code ein, z. B. #FF6A00. Die App passt die Farbe automatisch so an, dass Schrift immer gut lesbar bleibt.¦Escolha um campo de cor ou digite um código hex, ex.: #FF6A00. O app ajusta a cor automaticamente para o texto ficar sempre legível.
Darstellung¦Exibição
Animationen¦Animações
Anzeigegröße¦Tamanho de exibição
Oberfläche aktuell ×¦Interface atual ×
(Bildschirm¦(tela
dp breit). Standard ist 85 %: mehr Inhalt, Elemente bleiben gut lesbar. „Automatisch“ vergrößert die App auf sehr breiten Bildschirmen.¦dp de largura). O padrão é 85 %: mais conteúdo, os elementos continuam legíveis. „Automático“ aumenta o app em telas muito largas.
Schrift¦Fonte
Schriftart¦Tipo de fonte
Schriftgröße¦Tamanho da fonte
Aa – So sieht deine Schrift aus¦Aa – Assim fica sua fonte
Fünf klare Systemschriften, die auf jedem Gerät vorhanden sind. Die Schriftgröße wirkt zusätzlich zur Anzeigegröße.¦Cinco fontes de sistema claras, presentes em qualquer aparelho. O tamanho da fonte age junto com o tamanho de exibição.
Vorschau¦Prévia
Aktiv¦Ativo
Hervorhebung¦Destaque
Animationen „Aus“ schaltet die Vergrößerung beim Fokus ab, „Dezent“ macht sie schwächer. Das schont schwache Geräte.¦Animações „Desligado“ desativa o aumento ao focar; „Discreto“ deixa mais fraco. Isso alivia aparelhos fracos.
Design zurücksetzen¦Redefinir aparência
keiner seit dem Start¦nenhum desde o início
Version¦Versão
Bildschirm¦Tela
{0} × {1} dp · Dichte¦{0} × {1} dp · densidade
Inhalte¦Conteúdo
{0} Sender · {1} Filme · {2} Serien¦{0} canais · {1} filmes · {2} séries
Ladezustand¦Estado de carregamento
Programm (EPG)¦Programação (EPG)
{0} · {1} Sender¦{0} · {1} canais
lädt {0}/{1}¦carregando {0}/{1}
kein TMDB-Schlüssel¦sem chave TMDB
Letzter Fehler¦Último erro
Fehler seit Start¦Erros desde o início
Playlist neu laden¦Recarregar playlist
Bericht kopieren¦Copiar relatório
IPVIO Diagnose¦Diagnóstico do AI Stream TV
Der Bericht enthält keine Adressen, Benutzernamen oder Passwörter. Er wird nur in die Zwischenablage kopiert und nirgendwohin gesendet.¦O relatório não contém endereços, usuários ou senhas. Ele é apenas copiado para a área de transferência e não é enviado a lugar nenhum.
Tonspur¦Faixa de áudio
Automatische Genres¦Gêneros automáticos
Aus = Kategorien deines Anbieters¦Desligado = categorias do seu provedor
Automatische Genres sortieren Titel nach Genre (Anime = Animation aus Japan). Beim ersten Mal dauert das einige Minuten, danach ist alles gespeichert. Aus = die Kategorien deines Anbieters in der Reihenfolge deines Anbieters. In der Ansicht „Kategorien“ kannst du jederzeit zwischen beiden wechseln.¦Os gêneros automáticos organizam os títulos por gênero (anime = animação do Japão). Na primeira vez leva alguns minutos; depois fica tudo salvo. Desligado = as categorias do seu provedor na ordem dele. Na exibição „Categorias“ você pode alternar entre os dois a qualquer momento.
Automatische Genres brauchen den TMDB-Schlüssel in Config.kt.¦Os gêneros automáticos precisam da chave TMDB em Config.kt.
TV-Programm (EPG)¦Programação (EPG)
Programm bei {0} von {1} Sendern möglich¦Programação possível em {0} de {1} canais
XMLTV: {0}¦XMLTV: {0}
Die App sucht die Programmdaten automatisch (Xtream-Schnittstelle oder XMLTV-Adresse aus der Playlist). Wenn dein Anbieter eine eigene XMLTV-Adresse nennt, trage sie hier ein.¦O app busca os dados de programação automaticamente (interface Xtream ou endereço XMLTV da playlist). Se o seu provedor informar um endereço XMLTV próprio, coloque-o aqui.
Speichern & laden¦Salvar e carregar
Neu laden¦Recarregar
Sender oder Kategorien als Favorit markieren: OK gedrückt halten oder Menü-Taste.¦Marcar canais ou categorias como favoritos: segurar OK ou a tecla Menu.
Meine Playlists ({0})¦Minhas playlists ({0})
Aktivieren¦Ativar
Umbenennen¦Renomear
Löschen¦Excluir
Name der Playlist¦Nome da playlist
Name speichern¦Salvar nome
+ Weitere Playlist hinzufügen¦+ Adicionar outra playlist
Aktive Playlist neu laden¦Recarregar playlist ativa
Automatisch aktualisieren¦Atualizar automaticamente
Bei jedem Start¦A cada início
Täglich¦Diariamente
Wöchentlich¦Semanalmente
Nur von Hand¦Só manualmente
Playlist aktualisieren¦Atualizar playlist
Abstand¦Intervalo
alle {0} Stunden¦a cada {0} horas
Wochentag¦Dia da semana
Stunde¦Hora
Minute¦Minuto
Zuletzt aktualisiert:¦Última atualização:
noch nie¦nunca
Nächste Aktualisierung:¦Próxima atualização:
„Bei jedem Start“ lädt die Playlist bei jedem Öffnen der App. Die geplanten Varianten laufen auch im Hintergrund, solange das Gerät eingeschaltet und online ist, und holen verpasste Termine beim nächsten Start nach. „Nur von Hand“ nutzt immer den gespeicherten Stand; Neu laden geht jederzeit mit dem Knopf oben. Die Uhrzeit richtet sich nach deiner Zeitzone unter „Sprache & Region“.¦„A cada início“ carrega a playlist toda vez que o app abre. As opções agendadas também rodam em segundo plano enquanto o aparelho está ligado e online, e recuperam horários perdidos no próximo início. „Só manualmente“ usa sempre o que está salvo; recarregar pode a qualquer momento pelo botão acima. O horário segue o seu fuso em „Idioma e região“.
Zugangsdaten werden nur auf diesem Gerät gespeichert und hier nicht angezeigt. Es ist immer eine Playlist aktiv. Mit „Aktivieren“ wechselst du die Liste.¦Os dados de acesso ficam só neste aparelho e não são mostrados aqui. Há sempre uma playlist ativa. Com „Ativar“ você troca de lista.
IPVIO Stream · Version¦AI Stream TV · Versão
IPVIO Stream ist ein Player und liefert selbst keine Inhalte. Nutze nur Quellen, für die du eine Berechtigung hast.¦O AI Stream TV é um player e não fornece conteúdo. Use apenas fontes para as quais você tem permissão.
Dieses Produkt verwendet die TMDB-API, wird aber nicht von TMDB unterstützt oder zertifiziert. Intro-Zeiten: TheIntroDB.¦Este produto usa a API do TMDB, mas não é endossado nem certificado pelo TMDB. Horários de abertura: TheIntroDB.
Systemeinstellungen der App¦Configurações do sistema do app
Setzt Ansicht, Design, Menü, Player, Ton und Start auf die Standardwerte. Playlists, Favoriten, Verlauf und Fortsetzen-Positionen bleiben erhalten. Zwischenspeicher leerst du unter „Speicher“.¦Redefine Exibição, Aparência, Menu, Player, Áudio e Início para o padrão. Playlists, favoritos, histórico e posições de continuar são mantidos. O cache você limpa em „Armazenamento“.
Directing¦Direção
Acting¦Atuação
Writing¦Roteiro
Director¦Diretor
anzeigen ›¦mostrar ›
Download fehlgeschlagen (HTTP {0})¦Falha no download (HTTP {0})
Datei unplausibel¦Arquivo inválido
Offline oder nicht erreichbar¦Offline ou inacessível
{0} Trailer¦{0} trailers
Ratings¦Avaliações
Source¦Fonte
Value¦Valor
Episodes¦Episódios
Episode¦Episódio
Serif¦Serif
Leicht¦Leve
Monospace¦Monoespaçada
Sehr groß¦Muito grande
Riesig¦Enorme
Wiedergabe:¦Reprodução:
Keine Programmdaten erhalten¦Nenhum dado de programação recebido
Zurück mit der Zurück-Taste.¦Voltar com a tecla Voltar.
Deutsch¦Alemão
Englisch¦Inglês
Japanisch¦Japonês
Koreanisch¦Coreano
Französisch¦Francês
Spanisch¦Espanhol
Türkisch¦Turco
Kroatisch¦Croata
Serbisch¦Sérvio
Bosnisch¦Bósnio
Weiß¦Branco
Gelb¦Amarelo
Grün¦Verde
Cyan¦Ciano
Pink¦Rosa
Schwarz¦Preto
Keiner¦Nenhum
Kein¦Nenhum
"""
