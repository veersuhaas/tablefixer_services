package com.ivo.app.services.util;

public interface LocationSearchConstants {

    String GEO_COORDINATES = "GEO_COORDINATES";

    String USER_LOCATIONS = "USER_LOCATIONS";

    String SEARCH_KEYWORD = "SEARCH_KEYWORD";

	String CURRENT_GPS_LOCATION = "CURRENT_GPS_LOCATION";

	String USER_MY_PLACES = "USER_MY_PLACES";

	String CUSTOM_GPS_LOCATION = "CUSTOM_GPS_LOCATION";

	String QUERY_SEARCH_LOCATIONS_BY_GPS_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, "
			+ " loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country " +
			" from  location_info_ref loc " +
			" where " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <= :radius * 1609.344 ";

	String QUERY_SEARCH_BY_CUISINE_TYPES = "select loc.location_id locationId, loc.loc_uuid locationUUID, " +
			" loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, " +
			" loc.city city,loc.state state, loc.zip_code zip,loc.country country  " +
			" from  location_info_ref loc , location_tags_ref lt, location_tag_xref ltxref " +
			" where  " +
			" loc.loc_uuid=ltxref.loc_uuid " +
			" and lt.tag_id=ltxref.loc_tag_id " +
			" and Upper(lt.tag_name) like upper(:cuisineType) " +
			" and ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) " +
			" <= :radius * 1609.344 ";

	String QUERY_ADD_SEARCH_BY_RESTAURANT_NAME = " and upper(loc.loc_name) like upper(:locationName) ";


	String QUERY_ADD_ORDER_BY_DISTANCE_LOCATION_TABLE_REFERENCE = " order by ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) ";

	String QUERY_USER_FAVORITE_LOCATIONS_BY_GPS_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, "
			+ " loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite " +
			" from  location_info_ref loc, user_favorite_locations_xref favorite" +
			" where " +
			" loc.loc_uuid=favorite.location_uuid" +
			" and favorite.user_uuid=:userUuid " +
			" and ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) ";
	String QUERY_USER_FAVORITE_LOCATIONS_BY_DEFAULT = "select loc.location_id locationId, loc.loc_uuid locationUUID, " +
            " loc.loc_name locationName,  contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2,\n" +
            " loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite " +
            " from  location_info_ref loc, user_favorite_locations_xref favorite " +
            " where loc.loc_uuid=favorite.location_uuid " +
            " and favorite.user_uuid=:userUuid ";

String USER_LOCATION_UPDATE="UPDATE user_locations_xref  SET user_location_name = :userLocationName WHERE user_addr_id = :userAdrId";

String QUERY_USER_FAVORITE_LOCATIONS_BY_BOOK_MARKED_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.longitude,userloc.latitude), "+
        " ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber , loc.email email, loc.website ,loc.address_ln1 addrLn1 ,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite "+
        " from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc , user_favorite_locations_xref favorite "+
        " where "+
        " favorite.user_uuid=usr.user_uuid "+
		" and favorite.location_uuid=loc.loc_uuid  and userloc.user_uuid=favorite.user_uuid" +
        " and usr.user_uuid=:userUuid "+
        " and upper(userloc.user_location_name)=:userLocationType "+
        " and ST_DistanceSphere(ST_MakePoint(userloc.longitude,userloc.latitude), "+
        " ST_MakePoint(loc.lang,loc.lat)) "+
        " <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.longitude,userloc.latitude), "+
        " ST_MakePoint(loc.lang,loc.lat))";


String QUERY_GENERIC_ADDRESS_SEARCH = "select  name,county,state,zip_codes,longitude,latitude " +

			" from us_cities " +
			" where upper(name) like upper(:searchKey)  or upper(county) like upper(:searchKey) or zip_codes like :searchKey";

    String QUERY_PUBLIC_OPEN_EVENTS_HEAD = " select max_guests_allowed, SUM(CASE  WHEN guest.organizer_status='Accepted' THEN 1 ELSE 0 END) AS org_accepted_cnt," +
            " SUM(CASE WHEN guest.guest_status='Interested' THEN 1 ELSE 0 END) AS guest_interested_cnt,trans.event_uuid,trans.event_location_name,trans.event_loc_uuid, " +
            " trans.event_addr_ln1,trans.event_addr_ln2,trans.event_city,trans.event_country," +
            " trans.event_state,trans.event_zip,usr.user_uuid, usr.first_name || ' ' || usr.last_name as username," +
            " usr.facebook_link, usr.twitter_link,usr.linkedin_link, usr.contact_num," +
            " trans.event_from_dttm, trans.event_to_dttm,trans.event_desc,trans.event_short_desc,trans.organizer_uuid,trans.event_lang,trans.event_lat," +
            " ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat),   ST_MakePoint(:fromLongitude, :fromLatitude)) distance ,trans.event_age_bracket_start_yrs ageYearsStart,trans.event_age_bracket_end_yrs ageYearsEnd" +
            " from  event_details_trans trans " +
            " inner join user_info_ref usr on trans.organizer_uuid = usr.user_uuid " +
            " left join event_guest_sign_up_trans guest on trans.event_uuid= guest.event_uuid " +
            " where  trans.event_from_dttm >now() and event_age_bracket_start_yrs < event_age_bracket_end_yrs  ";

    String QUERY_USER_EVENTS_HEAD = " select CASE  WHEN trans.event_from_dttm>now() THEN true ELSE false END AS active,max_guests_allowed, SUM(CASE  WHEN guest.organizer_status='Accepted' THEN 1 ELSE 0 END) AS org_accepted_cnt," +
            " SUM(CASE WHEN guest.guest_status='Interested' THEN 1 ELSE 0 END) AS guest_interested_cnt,trans.event_uuid,trans.event_location_name,trans.event_loc_uuid, " +
            " trans.event_addr_ln1,trans.event_addr_ln2,trans.event_city,trans.event_country," +
            " trans.event_state,trans.event_zip,usr.user_uuid, usr.first_name || ' ' || usr.last_name as username," +
            " usr.facebook_link, usr.twitter_link,usr.linkedin_link, usr.contact_num," +
            " trans.event_from_dttm, trans.event_to_dttm,trans.event_desc,trans.event_short_desc,trans.organizer_uuid,trans.event_lang,trans.event_lat," +
            " ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat),   ST_MakePoint(:fromLongitude, :fromLatitude)) distance ,trans.event_age_bracket_start_yrs ageYearsStart,trans.event_age_bracket_end_yrs ageYearsEnd" +
            " from  event_details_trans trans " +
            " inner join user_info_ref usr on trans.organizer_uuid = usr.user_uuid " +
            " left join event_guest_sign_up_trans guest on trans.event_uuid= guest.event_uuid " +
            " where  trans.organizer_uuid=:userUUID and event_age_bracket_start_yrs < event_age_bracket_end_yrs  ";

    String QUERY_USER_EVENTS_TAIL = " and ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat), ST_MakePoint(:fromLongitude, :fromLatitude))<= :radius * 1609.344 " +
            " group by max_guests_allowed,trans.event_uuid,trans.event_location_name,trans.event_loc_uuid, " +
            " trans.event_addr_ln1,trans.event_addr_ln2,trans.event_city,trans.event_country," +
            " trans.event_state,trans.event_zip,usr.user_uuid, usr.first_name ,usr.last_name ," +
            " usr.facebook_link, usr.twitter_link,usr.linkedin_link, usr.contact_num, " +
            " trans.event_from_dttm, trans.event_to_dttm,trans.event_desc,trans.event_short_desc,trans.organizer_uuid, " +
            " trans.event_lang,trans.event_lat,trans.event_age_bracket_start_yrs,trans.event_age_bracket_end_yrs " +
            " order by event_from_dttm desc";

    String QUERY_PUBLIC_OPEN_EVENTS_TAIL = " and ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat), ST_MakePoint(:fromLongitude, :fromLatitude))<= :radius * 1609.344 " +
            " group by max_guests_allowed,trans.event_uuid,trans.event_location_name,trans.event_loc_uuid, " +
            " trans.event_addr_ln1,trans.event_addr_ln2,trans.event_city,trans.event_country," +
            " trans.event_state,trans.event_zip,usr.user_uuid, usr.first_name ,usr.last_name ," +
            " usr.facebook_link, usr.twitter_link,usr.linkedin_link, usr.contact_num, " +
            " trans.event_from_dttm, trans.event_to_dttm,trans.event_desc,trans.event_short_desc,trans.organizer_uuid, " +
            " trans.event_lang,trans.event_lat,trans.event_age_bracket_start_yrs,trans.event_age_bracket_end_yrs " +
            " order by ST_DistanceSphere(ST_MakePoint(trans.event_lang,trans.event_lat),   ST_MakePoint(:fromLongitude, :fromLatitude))";
}
