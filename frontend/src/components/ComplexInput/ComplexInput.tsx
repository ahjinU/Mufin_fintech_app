interface ComplexInput {
  label: string;
  width?: string;
  height?: string;
  message?: string;
  isMsg?: boolean;
  mode: 'ERROR' | 'INFORM' | 'NONE';
  children?: React.ReactElement;
}

export default function ComplexInput({
  label,
  width,
  height,
  children,
  message,
  isMsg = false,
  mode,
  ...props
}: ComplexInput) {
  return (
    <div
      className={`min-w-[16rem] ${width || 'w-full'} ${height || 'h-full'}`}
      {...props}
    >
      <label className="custom-semibold-text text-custom-black">{label}</label>
      <div className="mt-2">{children}</div>
      {message && isMsg && (
        <p
          className={`mt-1 custom-light-text ${
            mode === 'ERROR' ? 'text-custom-red' : 'text-custom-dark-gray'
          } text-[1.2rem] flex`}
        >
          {message}
        </p>
      )}
    </div>
  );
}
