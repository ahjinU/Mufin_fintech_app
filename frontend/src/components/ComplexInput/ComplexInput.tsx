interface ComplexInput {
  label: string;
  width?: string;
  errorMsg?: string;
  isError?: boolean;
  children?: React.ReactElement;
}

export default function ComplexInput({
  label,
  width,
  children,
  errorMsg,
  isError = false,
  ...props
}: ComplexInput) {
  return (
    <div className={`min-w-[16rem] ${width || 'w-full'}`} {...props}>
      <label className="custom-semibold-text text-custom-black">{label}</label>
      <div className="mt-2">{children}</div>
      {errorMsg && isError && (
        <p className="mt-1 custom-light-text text-custom-red text-[1.2rem] flex justify-end">
          {errorMsg}
        </p>
      )}
    </div>
  );
}
