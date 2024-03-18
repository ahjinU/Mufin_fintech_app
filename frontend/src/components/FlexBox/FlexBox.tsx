interface FlexBoxProps {
  isDivided: boolean;
  topChildren?: React.ReactElement;
  bottomChildren?: React.ReactElement;
}

function HorizontalLine() {
  return <div className="w-full h-[0.1rem] bg-custom-medium-gray"></div>;
}

export default function FlexBox({
  isDivided,
  topChildren,
  bottomChildren,
  ...props
}: FlexBoxProps) {
  return (
    <section
      className="w-full bg-custom-light-gray rounded-[2rem] p-[2.2rem] gap-[0.5rem]"
      {...props}
    >
      <>{topChildren}</>
      {isDivided && <HorizontalLine />}
      <>{bottomChildren}</>
    </section>
  );
}
