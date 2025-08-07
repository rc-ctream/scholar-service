package com.ctream.scholar.pois.utils;

import com.ctream.scholar.pois.api.mapper.response.CommentInfo;
import com.ctream.scholar.pois.models.Location;
import com.ctream.scholar.pois.models.POI;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor( access = PRIVATE )
public class SchoolarUtils {

    public static List<POI> createPois() {
        var list = new ArrayList<POI>();

        list.add(
            createPoi( "b7132d5a-35f3-4f3d-a7c0-7e3c4beea9f2", "Wilma Wunder Mainz",
                createLocation( "Markt 11", "55116", "Mainz", "Deutschland", 49.99996747212812, 8.273769815343154 ),
                "+49 6131 5401555", "info@mainzermonumente.de", "www.mainz.wilma-wunder.de", "Restaurant", 4.4,
                List.of(
                    createComment( "49896da4-688d-4b46-9c3c-f4f081709783",
                        "Wir waren hier an einem Montagabend zum Abendessen. Tisch problemlos spontan digital gebucht. Service ist sehr freundlich und professionell. Essen ist lecker und von guter Qualität, Preise im oberen Drittel, aber in Ordnung. Anfangs war es sehr laut im Lokal. Toiletten könnten etwas sauberer und im besseren Zustand sein. Auf jeden Fall keine schlechte Wahl.",
                        "2025-02-23",
                        "Tom Schmitz" )
                ), 1.0 ) );


        list.add( createPoi( "5b73a36d-81d9-4f8f-9c02-347e9b89b3e5", "Restaurant Esszimmer – Gonsberg",
            createLocation( "Im Niedergarten 16", "55124", "Mainz", "Deutschland", 49.99885047557297,
                8.218436284117857 ),
            "+49 6131 123456", "info@restaurant-esszimmer-gonsberg.de",
            "www.restaurant-esszimmer-gonsberg.de", "Restaurant", 4.9,
            List.of(
                createComment( "dfceec21-4225-4e65-a0aa-4ea1adfd4251", "Sehr gute Auswahl an Produkten.", "2025-02-20",
                    "Anna Müller" ) )
            , 2.0 ) );


        list.add( createPoi( "3c5d4a2d-83c2-47a4-b988-0c464cc800f6", "El Burro",
            createLocation( "Dominikanerstraße 2", "55116", "Mainz", "Deutschland", 49.99888909274939,
                8.269667186507366 ),
            "+49 6131 25390", "info@el-burro.de",
            "www.el-burro.de", "Kirche", 4.6,
            List.of(
                createComment( "2f517b66-ed55-45b8-b904-d30ad346a67c", "Toller Ort! Sehr empfehlenswert.", "2025-02-25",
                    "Max Mustermann" ) )
            , 0.5 ) );

        list.add( createPoi( "3a72db51-d8b2-44c2-b60a-e4c1231293e2", "Landesmuseum Mainz",
            createLocation( "Am Zollhafen 1", "55116", "Mainz", "Deutschland", 50.0000, 8.2735 ),
            "+49 6131 912490", "info@landesmuseum-mainz.de",
            "www.landesmuseum-mainz.de", "Museum", 4.7, new ArrayList<>(), 1.2 ) );

        list.add( createPoi( "ae68c12e-598f-4ab4-ae66-bd2db18da98d", "Kupferbergterasse",
            createLocation( "Kupferberg 1", "55116", "Mainz", "Deutschland", 50.0003, 8.2750 ),
            "+49 6131 22122", "info@kupferberg.de", "www.kupferberg.de",
            "Weingut", 4.6, new ArrayList<>(), 1.5 ) );

        list.add( createPoi( "d133ad4f-d133-41dd-93ff-71822925ce63", "Bayerischer Hof",
            createLocation( "Promenadeplatz 2-6", "80333", "München", "Deutschland", 48.1413, 11.5761 ),
            "+49 89 21200", "office@bayerischerhof.de",
            "www.bayerischerhof.de", "Hotel", 4.5,
            List.of(
                createComment( "0f7318d8-c897-41a8-9327-66060bb6f85a", "Toller Service und wunderschöne Zimmer.",
                    "2025-02-18",
                    "Sara Braun" ) )
            , 0.8 ) );

        list.add( createPoi( "8235ff15-ae2e-41a9-8cae-be65853248e7", "BMW Welt",
            createLocation( "Am Olympiapark 1", "80809", "München", "Deutschland", 48.1767, 11.5561 ),
            "+49 89 125016001", "info.bmwwelt@bmw-welt.com",
            "www.bmw-welt.com", "Museum", 4.6,
            List.of(
                createComment( "a4b3f689-908f-4e93-94a8-2c7a6377b868",
                    "Großartige Ausstellung und interessante Führungen.", "2025-02-24",
                    "Michael Schmidt" ) )
            , 5.0 ) );

        list.add( createPoi( "0644cd33-ba75-409e-85c6-75c30eb686b7", "Freie Universität Berlin",
            createLocation( "Kaiserswerther Str. 16-18", "14195", "Berlin", "Deutschland", 52.4560, 13.2904 ),
            "+49 30 83810", "info@fu-berlin.de",
            "www.fu-berlin.de", "Universität", 4.3,
            List.of(
                createComment( "b42c4180-913e-48db-a303-4566816ae586", "Top Fakultät und Campus, gute Angebote.",
                    "2025-02-22",
                    "Sabine Lang" ) )
            , 2.2 ) );

        list.add( createPoi( "d7648131-944c-4ba4-874d-1478ee51ae01", "Berliner Dom",
            createLocation( "Am Lustgarten", "10178", "Berlin", "Deutschland", 52.5191, 13.4012 ),
            "+49 30 20269136", "info@berlinerdom.de",
            "www.berlinerdom.de", "Kirche", 4.7, new ArrayList<>(), 0.5 ) );

        list.add( createPoi( "9844a823-7eaa-4108-8db5-a537276616ed", "Kurfürstendamm",
            createLocation( "Kurfürstendamm", "10719", "Berlin", "Deutschland", 52.5018, 13.3179 ),
            "N/A", "N/A", "N/A",
            "Einkaufsstraße", 4.3, new ArrayList<>(), 3.8 ) );

        list.add( createPoi( "af72c11e-720f-4bd4-ae67-bb3db19ea99d", "Staatstheater Mainz",
            createLocation( "Gutenbergpl. 7", "55116", "Mainz", "Deutschland", 50.000473, 8.272215 ),
            "+49 6131 28510", "info@staatstheater-mainz.de",
            "www.staatstheater-mainz.com", "Theater", 4.5, new ArrayList<>(), 0.6 ) );

        list.add( createPoi( "bf72c11f-730f-4cd4-ae68-bc3db19ea99e", "Café Extrablatt Mainz",
            createLocation( "Neubrunnenpl. 20", "55116", "Mainz", "Deutschland", 50.000149, 8.270880 ),
            "+49 6131 9725398", "info@cafe-extrablatt-mainz.de",
            "www.cafe-extrablatt.com", "Café", 4.3, new ArrayList<>(), 0.2 ) );

        list.add( createPoi( "cf72c110-740f-4dd4-be69-bd3db19ea99f", "Hohe Domkirche St. Martin zu Mainz",
            createLocation( "Markt 10", "55116", "Mainz", "Deutschland", 49.999796, 8.274436 ),
            "+49 6131 253412", "info@hoherdom-mainz.de",
            "www.bistummainz.de/dom", "Kathedrale", 4.7, new ArrayList<>(), 0.4 ) );

        list.add( createPoi( "df72c120-750f-4ed4-ae70-be3db19ea9a0", "Mainzer Zitadelle",
            createLocation( "Zitadellenweg", "55131", "Mainz", "Deutschland", 50.000956, 8.275802 ),
            "+49 6131 123456", "info@zitadelle-mainz.de",
            "www.mainz.de/zitadelle", "Sehenswürdigkeit", 4.6, new ArrayList<>(), 1.0 ) );

        list.add( createPoi( "ef72c121-761f-4fd4-ae71-bf3db19eaa1b", "Gutenberg-Museum",
            createLocation( "Liebfrauenpl. 5", "55116", "Mainz", "Deutschland", 49.999703, 8.273281 ),
            "+49 6131 122686", "info@gutenberg-museum.de",
            "www.gutenberg-museum.de", "Museum", 4.4, new ArrayList<>(), 0.5 ) );

        list.add( createPoi( "ff72c122-770f-4gd4-ae72-bg3db19ea9a1c", "Heiliggeist",
            createLocation( "Mailandsgasse 11", "55116", "Mainz", "Deutschland", 50.000771, 8.271228 ),
            "+49 6131 225432", "info@heiliggeist.de",
            "www.heiliggeist-mainz.de", "Restaurant", 4.7, new ArrayList<>(), 0.3 ) );

        list.add( createPoi( "0f82c123-780f-4hd4-ae73-bh3db19ea9a1d", "Adaccio",
            createLocation( "Holzstraße 40", "55116", "Mainz", "Deutschland", 49.999860, 8.268924 ),
            "+49 6131 233445", "info@adaccio.de",
            "www.adaccio-mainz.de", "Restaurant", 4.4, new ArrayList<>(), 0.5 ) );

        list.add( createPoi( "1f82c124-790f-4id4-ae74-bi3db19ea9a1e", "Zum goldene Hirsch",
            createLocation( "Hirschgartenstraße 14", "55120", "Mainz", "Deutschland", 50.003243, 8.253337 ),
            "+49 6131 2102473", "info@hirsch-mainz.de",
            "www.hirsch-mainz.de", "Restaurant", 4.5, new ArrayList<>(), 3.2 ) );

        list.add( createPoi( "2f82c125-800f-4jd4-ae75-bj3db19ea9a1f", "Zorn'sches Haus",
            createLocation( "Schillerpl. 11", "55116", "Mainz", "Deutschland", 50.001703, 8.272876 ),
            "+49 6131 9725398", "info@zornshaus.de",
            "www.zornshaus-mainz.de", "Restaurant", 4.3, new ArrayList<>(), 0.6 ) );

        list.add( createPoi( "3f82c126-810f-4jd4-ae76-bk3db19ea9a1g", "La Rustica",
            createLocation( "Quintinsstraße 17", "55116", "Mainz", "Deutschland", 49.999267, 8.273996 ),
            "+49 6131 9725400", "info@la-rustica.de",
            "www.la-rustica-mainz.de", "Restaurant", 4.6, new ArrayList<>(), 0.4 ) );

        list.add( createPoi( "9f8b6d72-6150-4175-81eb-62359782cb40", "Barbaros Moschee",
            createLocation( "Haifa-Allee 34", "55128", "Mainz", "Deutschland", 49.96885958912696, 8.23220421681118 ),
            "+49 6131 688977", "info@igmg-mainz.de",
            "www.igmg-mainz.de", "Moschee", 4.8,
            List.of(
                createComment( UUID.randomUUID().toString(), "Sehr gut Atmosphäre,, kurze predigt ,,\n" +
                                                             "zwei Sprachen, türkische und deutsche Freitagspredigt ,, Klimaanlage im Sommer ,,\n" +
                                                             "Trinken und Essen für günstige Preise.", "2023-02-01",
                    "Ali Baba" ),
                createComment( UUID.randomUUID().toString(),
                    "Nette kleine Moschee mit kleinem Supermarkt ( aktuell geschlossen) Lecker Döner gegessen. Service nett Preis Leistung ok."
                    , "2022-03-01", "Dieter Malisius" )
            )
            , 1.4 ) );

        list.add( createPoi( "c10fcc3a-7cdd-4e8e-9795-7244f15eff37", "DITIB Yunus Emre Moschee Mainz",
            createLocation( "Rheingauwall 1", "55122", "Mainz", "Deutschland", 50.012239814401426, 8.244443073226279 ),
            "+49 6131 373303", "info@igmg-mainz.de",
            "www.igmg-mainz.de", "Moschee", 4.8,
            List.of(
                createComment( "a28ea676-93ab-4a78-89d3-3db414ac0150",
                    "Die Moschee hat mir sehr gefallen abwechselreiche feste und Gastfreundliche Menschen. Das Restaurant ist immer sauber und das Essen schmeckt sehr gut. Außerdem gibt es ein Friseur der auch gute Angestellte hat.\n" +
                    "Kann ich nur weiter empfehlen", "2025-02-22", "Reiner Boos" )
            )
            , 2.4 ) );

        return list;
    }

    private static CommentInfo createComment( String id, String comment, String createdAt, String author ) {
        return CommentInfo.builder()
            .id( id )
            .comment( comment )
            .createdAt( createdAt )
            .author( author )
            .build();
    }

    private static POI createPoi( String id, String name, Location location, String phone, String email,
                                  String website, String category, double rates, List<CommentInfo> comments,
                                  double distance ) {
        return POI.builder()
            .id( id )
            .name( name )
            .location( location )
            .phone( phone )
            .email( email )
            .website( website )
            .category( category )
            .rates( rates )
            .comments( comments )
            .distance( distance )
            .build();
    }

    private static Location createLocation( String street, String postalCode, String city, String country,
                                            double latitude, double longitude ) {
        return Location.builder()
            .street( street )
            .postalCode( postalCode )
            .city( city )
            .country( country )
            .latitude( latitude )
            .longitude( longitude )
            .build();
    }
}
