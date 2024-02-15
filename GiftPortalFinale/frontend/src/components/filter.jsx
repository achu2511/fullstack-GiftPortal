import React from 'react';
// import ReactDOM from 'react-dom';
import { Pagination, Toggle,  TagPicker } from 'rsuite';

const Switch = ({ label, checked, onChange }) => {
  return (
    <span>
      {label}：
      <Toggle checked={checked} onChange={onChange} />
    </span>
  );
};

const limitOptions = [30, 50, 100];

const LayoutSettings = ({ layout, setLayout }) => {
  return (
    <span>
      layout：
      <TagPicker
        value={layout}
        onChange={setLayout}
        cleanable={false}
        searchable={false}
        data={[
          { value: 'total', label: 'total' },
          { value: '-', label: '-' },
          { value: 'pager', label: 'pager' },
          { value: '|', label: '|' },
          { value: 'limit', label: 'limit' },
          { value: 'skip', label: 'skip' }
        ]}
      />
    </span>
  );
};

const App = () => {

  const [activePage, setActivePage] = React.useState(1);

  const [layout, setLayout] = React.useState(['total', '-', 'limit', '|', 'pager', 'skip']);
  const [limit, setLimit] = React.useState(50);

  return (
    <>
      <div>
        {/* ... other settings components ... */}
        <hr />
        <LayoutSettings layout={layout} setLayout={setLayout} />
      </div>

      <hr />
      <Pagination
        layout={layout}
        size={size}
        prev={prev}
        next={next}
        first={first}
        last={last}
        ellipsis={ellipsis}
        boundaryLinks={boundaryLinks}
        total={total}
        limit={limit}
        limitOptions={limitOptions}
        maxButtons={maxButtons}
        activePage={activePage}
        onChangePage={setActivePage}
        onChangeLimit={setLimit}
      />
    </>
  );
};

export default Switch;
