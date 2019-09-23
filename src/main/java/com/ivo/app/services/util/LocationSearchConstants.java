package com.ivo.app.services.util;

public interface LocationSearchConstants {

	String GEO_COORDINATES = "GEO_COORDINATES";

	String USER_LOCATIONS = "USER_LOCATIONS";

	String SEARCH_KEYWORD = "SEARCH_KEYWORD";

	String CURRENT_GPS_LOCATION = "CURRENT_GPS_LOCATION";

	String USER_MY_PLACES = "USER_MY_PLACES";

	String CUSTOM_GPS_LOCATION = "CUSTOM_GPS_LOCATION";


	String QUERY_SEARCH_LOCATIONS_BY_BOOK_MARKED_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " +
			"ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber , loc.email email, loc.website ,loc.address_ln1 addrLn1 ,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,loc.country country" +
			" from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc " +
			" where " +
			" userloc.user_uuid=usr.user_uuid " +
			" and usr.user_uuid=:UUID " +
			" and upper(userloc.user_location_type)=upper(:locationType)" +
			" and ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <=:radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat)," +
			" ST_MakePoint(loc.lang,loc.lat)) ";
	String QUERY_SEARCH_LOCATIONS_BY_GPS_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, "
			+ " loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country " +
			" from  location_info_ref loc " +
			" where " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
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

	String QUERY_USER_FAVORITE_LOCATIONS_BY_BOOK_MARKED_COORDINATES = "select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber , loc.email email, loc.website ,loc.address_ln1 addrLn1 ,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite" +
			" from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc , user_favorite_locations_xref favorite " + 
			" where  " + 
			" userloc.user_id=usr.user_id  " + 
			" and favorite.user_uuid=usr.user_uuid " + 
			" and favorite.location_uuid=loc.loc_uuid " + 
			" and usr.user_uuid=:userUuid " +
			" and upper(userloc.user_location_type)=:userLocationType " +
			" and ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " + 
			" ST_MakePoint(loc.lang,loc.lat))" + 
			" <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat)," + 
			" ST_MakePoint(loc.lang,loc.lat)) ";


	String QUERY_GENERIC_ADDRESS_SEARCH = "select  name,county,state,zip_codes,longitude,latitude " +
			" from us_cities " +
			" where upper(name) like upper(:searchKey)  or upper(county) like upper(:searchKey) or zip_codes like :searchKey";
}
