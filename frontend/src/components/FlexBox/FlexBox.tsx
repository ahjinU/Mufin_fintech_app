interface FlexBoxProps {
  isDivided: boolean;
  topChildren?: React.ReactElement;
  bottomChildren?: React.ReactElement;
  date?: string;
  mode?: 'LIST' | 'SHOW';
}

function HorizontalLine() {
  return <div className="w-full h-[0.1rem] bg-custom-medium-gray"></div>;
}

export default function FlexBox({
  mode = 'SHOW',
  isDivided,
  topChildren,
  bottomChildren,
  date,
  ...props
}: FlexBoxProps) {
  return (
    <div>
      {date && <p className="text-custom-dark-gray ml-[1rem]">{date}</p>}
      <section
        className={`w-full flex flex-col bg-custom-light-gray rounded-[2rem]
        ${
          mode === 'LIST' ? 'p-[1rem]' : 'p-[2.2rem]'
        } px-[1.5rem] gap-[1.4rem]`}
        {...props}
      >
        <>{topChildren}</>
        {isDivided && <HorizontalLine />}
        <>{bottomChildren}</>
      </section>
    </div>
  );
}
