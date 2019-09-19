package com.ivo.app.services.util;

public interface LocationSearchConstants {
	
	public static final String GEO_COORDINATES= "GEO_COORDINATES";
	
	public static final String USER_LOCATIONS= "USER_LOCATIONS";
	
	public static final String CUSTOM_ADDRESS= "CUSTOM_ADDRESS";


	public static final String QUERY_SEARCH_LOCATIONS_BY_BOOK_MARKED_COORDINATES= "select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " +
			"ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber , loc.email email, loc.website ,loc.address_ln1 addrLn1 ,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,loc.country country" +
			" from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc " +
			" where " +
			" userloc.user_uuid=usr.user_uuid " +
			" and usr.user_uuid=:UUID " +
			" and upper(userloc.user_location_type)=upper(:locationtype)" +
			" and ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <=:radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat)," +
			" ST_MakePoint(loc.lang,loc.lat)) ";
	public static final String QUERY_SEARCH_LOCATIONS_BY_GPS_COORDINATES= "select loc.location_id locationId, loc.loc_uuid locationUUID, "
			+ " loc.loc_name locationName, " +
			" ST_DistanceSphere(ST_MakePoint(:lng, :lat),  ST_MakePoint(loc.lang,loc.lat)) distance," +
			" contact_num_1 phoneNumber, loc.email email, loc.website ,loc.address_ln1 addrLn1,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country " +
			" from  location_info_ref loc, user_favorite_locations_xref favorite" +
			" where " +
			" loc.loc_uuid=favorite.location_uuid" +
			" and ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat))" +
			" <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(:lng, :lat), " +
			" ST_MakePoint(loc.lang,loc.lat)) ";

	public static final String QUERY_USER_FAVORITE_LOCATIONS_BY_GPS_COORDINATES= "select loc.location_id locationId, loc.loc_uuid locationUUID, "
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

	public static final String QUERY_USER_FAVORITE_LOCATIONS_BY_BOOK_MARKED_COORDINATES= "select loc.location_id locationId, loc.loc_uuid locationUUID, loc.loc_name locationName, ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " + 
			" ST_MakePoint(loc.lang,loc.lat)) distance,contact_num_1 phoneNumber , loc.email email, loc.website ,loc.address_ln1 addrLn1 ,loc.address_ln2 addrLn2, loc.city city,loc.state state, loc.zip_code zip,loc.country country,true favorite" +
			" from  location_info_ref loc, user_info_ref usr, user_locations_xref userloc , user_favorite_locations_xref favorite " + 
			" where  " + 
			" userloc.user_id=usr.user_id  " + 
			" and favorite.user_uuid=usr.user_uuid " + 
			" and favorite.location_uuid=loc.loc_uuid " + 
			" and usr.user_uuid=:userUuid " + 
			" and upper(userloc.user_location_type)=:userlocationtype " + 
			" and ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat), " + 
			" ST_MakePoint(loc.lang,loc.lat))" + 
			" <= :radius * 1609.344 order by ST_DistanceSphere(ST_MakePoint(userloc.lang,userloc.lat)," + 
			" ST_MakePoint(loc.lang,loc.lat)) ";



}
