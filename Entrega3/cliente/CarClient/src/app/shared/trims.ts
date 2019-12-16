export interface Trim {
  model_id: string;
  model_make_id: string;
  model_name: string;
  model_trim: string;
  model_year: string;
  model_body: string;
  model_engine_position: string;
  model_engine_cc: string;
  model_engine_cyl: string;
  model_engine_type: string;
  model_engine_valves_per_cyl: string;
  model_engine_power_ps: string;
  model_engine_power_rpm?: any;
  model_engine_torque_nm: string;
  model_engine_torque_rpm?: any;
  model_engine_bore_mm?: any;
  model_engine_stroke_mm?: any;
  model_engine_compression: string;
  model_engine_fuel: string;
  model_top_speed_kph?: any;
  model_0_to_100_kph?: any;
  model_drive: string;
  model_transmission_type: string;
  model_seats?: any;
  model_doors: string;
  model_weight_kg?: any;
  model_length_mm?: any;
  model_width_mm?: any;
  model_height_mm?: any;
  model_wheelbase_mm?: any;
  model_lkm_hwy: string;
  model_lkm_mixed: string;
  model_lkm_city: string;
  model_fuel_cap_l?: any;
  model_sold_in_us: string;
  model_co2?: any;
  model_make_display: string;
  make_display: string;
  make_country: string;
}

export interface CarResponse {
  Trims: Trim[];
}
