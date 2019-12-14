export interface LoginMessage {
  // Esto nos permite dejar este parámetro opcional, dado que solo se va a usar en el caso de que todo vaya bn
  nif?: string;
  message: string;
}
